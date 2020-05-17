package com.barrett.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * IO流操作步骤
 * 1、创建源
 * 2、选择流
 * 3、操作（读）
 * 4、释放资源
 *
 * @Author created by barrett in 2020/5/14 23:05
 */
public class ReadDemo {

    public static void main(String[] args) {
        test3();
    }

    static void test3() {
        File url = new File("io.txt");

        Reader is = null;
        try {
            is = new FileReader(url);
            char[] flush = new char[5];//缓冲容器
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
