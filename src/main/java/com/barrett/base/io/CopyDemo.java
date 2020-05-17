package com.barrett.base.io;

import java.io.*;

/**
 * 文件的拷贝
 *
 * @Author created by barrett in 2020/5/15 22:50
 */
public class CopyDemo {

    public static void main(String[] args) {
        copy();
    }

    static void copy() {
        File url = new File("io.txt");
        File dest = new File("io-copy.txt");

        InputStream is = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(url);
            os = new FileOutputStream(dest);

            byte[] flush = new byte[5];//缓冲容器
            int len;//接收长度
            while ((len = is.read(flush)) != -1) {
                os.write(flush, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void copy2() {
        File url = new File("io.txt");
        File dest = new File("io-copy.txt");

        //1.7引入此写法，会自动关闭流
        try (
                InputStream is = new FileInputStream(url);
                FileOutputStream os = new FileOutputStream(dest);
        ) {


            byte[] flush = new byte[5];//缓冲容器
            int len;//接收长度
            while ((len = is.read(flush)) != -1) {
                os.write(flush, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
