package com.barrett.http.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class BaseCtrl {
    private static final String prefix = "src/main/resources/temp/";

    static String readJsonFile(String file) {
        File url = new File(prefix+file);

        String str = "";
        Reader is = null;
        try {
            is = new FileReader(url);
            char[] flush = new char[5];//缓冲容器
            int len;//接收长度
            while ((len = is.read(flush)) != -1) {
                //字符数组->字符串解码的过程，传入实际长度（避免空数据    ）
                str += new String(flush, 0, len);
            }
//                System.out.print(str);
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
        return str;
    }
}
