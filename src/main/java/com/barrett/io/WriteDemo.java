package com.barrett.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 文件字节输出流
 * 1、创建源
 * 2、选择流
 * 3、操作（读）
 * 4、释放资源
 * @Author created by barrett in 2020/5/15 22:24
 */
public class WriteDemo {
    public static void main(String[] args) {
        test1();
    }

    static void test1(){
        //创建源
        File dest = new File("dest.txt");
        Writer os=null;
        try {
            //选择流
            os = new FileWriter(dest);
            String str = "it is so sasy! 信心、耐心";
            //操作（写）：字符串->字节数组（编码的过程）
            //方式一
//            char[] c = str.toCharArray();
//            os.write(c,0,c.length);
            //方式二
//            os.write(str);

            //方式三
            os.append(str).append("我是后来居上");


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
