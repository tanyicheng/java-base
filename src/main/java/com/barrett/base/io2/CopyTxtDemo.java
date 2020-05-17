package com.barrett.base.io2;

import java.io.*;

/**
 * 文件的拷贝
 * 使用字符缓冲流 Buffered*比File*速度快
 * @Author created by barrett in 2020/5/15 22:50
 */
public class CopyTxtDemo {

    public static void main(String[] args) {
        copy();
    }


    static void copy() {
        File url = new File("src/main/resources/file/io.txt");
        File dest = new File("io-copy.txt");

        //1.7引入此写法，会自动关闭流 try-with-resource
        try (
                BufferedReader is = new BufferedReader(new FileReader(url));
                BufferedWriter os = new BufferedWriter(new FileWriter(dest));
        ) {

            String str;//接收长度
            while ((str = is.readLine()) != null) {
                os.write(str);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
