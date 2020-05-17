package com.barrett.io;

import java.io.*;

/**
 * 文件读取到字节数组中
 * 从字节数组中读取并写入文件
 *
 * @Author created by barrett in 2020/5/16 14:27
 */
public class ByteArrayTest {

    public static void main(String[] args) {
        byte[] bytes = fileToByteArray("src/main/resources/img/man.gif");

        byteArrayToFile(bytes, "src/main/resources/img/man-byte.gif");
    }

    /**
     * 读取图片到字节数组
     * 1、图片到程序 FileInputStream
     * 2、程序到字节数组 ByteArrayOutputStream
     *
     * @Author created by barrett in 2020/5/16 14:29
     */
    static byte[] fileToByteArray(String filePath) {
        File file = new File(filePath);

        InputStream is = null;
        ByteArrayOutputStream os = null;
        try {
            is = new FileInputStream(file);
            os = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            os.flush();

            System.out.println(os.size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return os.toByteArray();
    }

    /**
     * 字节数组写出到图片
     * 1、字节数组到程序 ByteArrayInputStream
     * 2、程序到图片 FileOutputStream
     *
     * @Author created by barrett in 2020/5/16 14:30
     */
    static void byteArrayToFile(byte[] src, String filePath) {

        //创建源
        File file = new File(filePath);
        //选择流
        InputStream is=null;

        try {
            is = new ByteArrayInputStream(src);
            OutputStream os = new FileOutputStream(file);

            //操作，分段读取
            byte[] bytes = new byte[1024];
            int len;
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            os.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
