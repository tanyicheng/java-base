package com.barrett.base.io.io1;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 文件的工具类
 * @Author created by barrett in 2020/5/16 17:17
 */
public class FileUtils {

    public static void main(String[] args) {
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("src/main/resources/file/dest.txt"));
            OutputStream os = new BufferedOutputStream(new FileOutputStream("src/main/resources/file/dest-2.txt"));
            copy(is,os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] bytes=null;
        //图片
        try {
            InputStream is = new FileInputStream("src/main/resources/img/man.gif");
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            //todo 这种方式也行,更直接
//            OutputStream os = new FileOutputStream("src/main/resources/img/man-2.gif");
            copy(is,os);
            bytes = os.toByteArray();
            System.out.println(bytes.length);

            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            FileOutputStream fos = new FileOutputStream("src/main/resources/img/man-2.gif");
            copy(bis,fos);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 文件拷贝
     * @Author created by barrett in 2020/5/16 21:26
     */
    public static void copy(InputStream is , OutputStream os){

        try{
            int len;
            byte[] bytes = new byte[1024];
            while((len=is.read(bytes))!=-1){
                os.write(bytes,0,len);
            }
            os.flush();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(is,os);
        }

    }

    /**
     * 通用的关闭流
     * @Author created by barrett in 2020/5/16 16:59
     */
    public static void close(Closeable... ios){
        for (Closeable io : ios) {
            if(io != null) {
                try {
                    io.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String imgToBase64(String path) {
        String baseStr = "";
        File file = new File(path);
        try {
            InputStream input = new FileInputStream(file);
            // 加密
            BASE64Encoder encoder = new BASE64Encoder();
            byte[] bytes = IOUtils.toByteArray(input);

            baseStr = "data:image/png" + ";base64," + encoder.encode(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseStr;
    }

    public static String imgToBase64ByInputStream(String url) {
        String baseStr = "";
        try {
            InputStream input = getImageStream( url);
            // 加密
            BASE64Encoder encoder = new BASE64Encoder();
            byte[] bytes = IOUtils.toByteArray(input);

            baseStr = "data:image/png" + ";base64," + encoder.encode(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseStr;
    }

    public static InputStream getImageStream(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return inputStream;
            }
        } catch (IOException e) {
            System.out.println("获取网络图片出现异常，图片路径为：" + url);
            e.printStackTrace();
        }
        return null;
    }

}
