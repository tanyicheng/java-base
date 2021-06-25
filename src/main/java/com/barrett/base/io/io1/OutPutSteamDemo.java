package com.barrett.base.io.io1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件字节输出流
 * 1、创建源
 * 2、选择流
 * 3、操作（读）
 * 4、释放资源
 * @Author created by barrett in 2020/5/15 22:24
 */
public class OutPutSteamDemo {
    public static void main(String[] args) {
        test1();
    }

    static void test1(){
        //创建源
        File dest = new File("dest.txt");
        FileOutputStream os=null;
        try {
            //选择流
            os = new FileOutputStream(dest);
            String str = "it is so sasy!";
            //操作（写）：字符串->字节数组（编码的过程）
            byte[] bytes = str.getBytes();
            os.write(bytes,0,bytes.length);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
