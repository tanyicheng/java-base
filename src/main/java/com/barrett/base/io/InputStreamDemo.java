package com.barrett.base.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * IO输入流操作步骤
 * 1、创建源
 * 2、选择流
 * 3、操作（读）
 * 4、释放资源
 *
 * @Author created by barrett in 2020/5/14 23:05
 */
public class InputStreamDemo {

    public static void main(String[] args) {
        test3();

    }

    //基础流出
    static void test1() {
        //源头：文件路径
        File file = new File("readme.txt");

        try {
            //操作方式：字节读取
            InputStream is = new FileInputStream(file);

            int read1 = is.read();
            int read2 = is.read();
            int read3 = is.read();

            System.out.println((char) read1);
            System.out.println((char) read2);
            System.out.println((char) read3);
            //释放
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //标准写法
    static void test2() {
        File url = new File("io.txt");

        InputStream is = null;
        try {
            is = new FileInputStream(url);
            int read;
            while ((read = is.read()) != -1) {
                System.out.print((char) read);
            }
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

    /**
     * 使用字节
     * @Author created by barrett in 2020/5/15 22:53
     */
    static void test3() {
        File url = new File("io.txt");

        InputStream is = null;
        try {
            is = new FileInputStream(url);
            byte[] flush = new byte[5];//缓冲容器
            int len;//接收长度
            while ((len = is.read(flush)) != -1) {
                //字符数组->字符串解码的过程，传入实际长度（避免空数据    ）
                String s = new String(flush,0,len);
                System.out.print(s);
            }
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
}
