package com.barrett.oss;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 仅参考，不使用
 *
 * @Author created by barrett in 2022/11/22 05:59
 */
public class QiNiuUtil {
    private static final String secretKey = QiNiuConfig.getInstance().getSecretKey();
    private static final String bucket = QiNiuConfig.getInstance().getBucket();
    private static final Region region = QiNiuConfig.getInstance().getRegion();
    private static String accessKey = QiNiuConfig.getInstance().getAccessKey();

    /**
     * 上传本地文件
     *
     * @param localFilePath 本地文件完整路径
     * @param key           文件云端存储的名称，值为null时生成随机的文件名，同名文件会发生覆盖
     * @return
     */
    public static String upload(String localFilePath, String key) {
        UploadManager uploadManager = getUploadManager();
        //...生成上传凭证，然后准备上传
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken;
        if (key != null) {
            upToken = auth.uploadToken(bucket, key);//覆盖上传凭证
        } else {
            upToken = auth.uploadToken(bucket);
        }
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet.key;
        } catch (QiniuException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 上传MultipartFile
     *
     * @param file
     * @param key  文件云端存储的名称，值为null时生成随机的文件名，同名文件会发生覆盖
     * @return
     * @throws IOException
     */
    public static String uploadMultipartFile(MultipartFile file, String key) {
        UploadManager uploadManager = getUploadManager();
        //把文件转化为字节数组
        InputStream is = null;
        ByteArrayOutputStream bos = null;

        try {
            is = file.getInputStream();
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = is.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            byte[] uploadBytes = bos.toByteArray();

            Auth auth = getAuth();
            String upToken;
            if (key != null) {
                upToken = auth.uploadToken(bucket, key);//覆盖上传凭证
            } else {
                upToken = auth.uploadToken(bucket);
            }
            //默认上传接口回复对象
            DefaultPutRet putRet;
            //进行上传操作，传入文件的字节数组，文件名，上传空间，得到回复对象
            Response response = uploadManager.put(uploadBytes, key, upToken);
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet.key;
        } catch (QiniuException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取所有的bucket
     */
    public static String[] getBucketsInfo() {
        try {
            BucketManager bucketManager = getBucketManager();
            //获取所有的bucket信息
            String[] buckets = bucketManager.buckets();
            return buckets;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件访问地址
     *
     * @param fileName 文件云端存储的名称
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String fileUrl(String fileName) throws UnsupportedEncodingException {
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String publicUrl = String.format("%s/%s", bucket, encodedFileName);
        Auth auth = getAuth();
        long expireInSeconds = QiNiuConfig.getInstance().getExpireInSeconds();
        if (-1 == expireInSeconds) {
            return auth.privateDownloadUrl(publicUrl);
        }
        return auth.privateDownloadUrl(publicUrl, expireInSeconds);
    }

    /**
     * 获取bucket里面所有文件的信息
     */
    public static FileInfo[] getFileInfo() {
        try {
            BucketManager bucketManager = getBucketManager();
            //文件名前缀
            String prefix = "";
            //每次迭代的长度限制，最大1000，推荐值 1000
            int limit = 1000;
            //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
            String delimiter = "";

            //列举空间文件列表
            BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, prefix, limit, delimiter);
            while (fileListIterator.hasNext()) {
                //处理获取的file list结果
                FileInfo[] items = fileListIterator.next();
                return items;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除多个文件
     *
     * @param keys 文件名称数组
     * @return
     */
    public static Map<String, String> deletes(String... keys) {
        Map<String, String> map = new HashMap<>();
        try {
            //设定删除的数据
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucket, keys);
            BucketManager bucketManager = getBucketManager();
            //发送请求
            Response response = bucketManager.batch(batchOperations);

            //返回数据
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < keys.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = keys[i];
                if (status.code == 200) {
                    map.put(key, "delete success");
                } else {
                    map.put(key, "delete failed");
                }
            }
        } catch (Exception e) {
        }
        return map;
    }

    /**
     * 删除bucket中的文件名称
     *
     * @param key 文件名称
     * @return
     */
    public static boolean delete(String key) {
        try {
            BucketManager mg = getBucketManager();
            mg.delete(bucket, key);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Auth getAuth() {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth;
    }

    /**
     * 获取上传管理器
     *
     * @return
     */
    public static UploadManager getUploadManager() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(region);
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        return uploadManager;
    }

    /**
     * 获取Bucket的管理对象
     *
     * @return
     */
    public static BucketManager getBucketManager() {
        Configuration cfg = new Configuration(region);
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        return bucketManager;
    }

}