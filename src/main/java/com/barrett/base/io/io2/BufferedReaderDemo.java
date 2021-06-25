package com.barrett.base.io.io2;

import java.io.*;

/**
 * 只能操作文本
 * @Author created by barrett in 2020/5/14 23:05
 */
public class BufferedReaderDemo {

    public static void main(String[] args) {
        test3();
    }

    static void test3() {
        File url = new File("dest.txt");

        BufferedReader is = null;
        try {
            is = new BufferedReader(new FileReader(url));
            String str;//接收
            while ((str = is.readLine()) != null) {
                System.out.print(str);
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
