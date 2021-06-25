package com.barrett.base.io.io2;

import java.io.*;

/**
 * 只能操作文本
 * @Author created by barrett in 2020/5/15 22:24
 */
public class BufferedWriterDemo {
    public static void main(String[] args) {
        test1();
    }

    static void test1(){
        //创建源
        File dest = new File("dest.txt");
        BufferedWriter os=null;
        try {
            //选择流
            os = new BufferedWriter(new FileWriter(dest));
            String str = "it is so sasy! 信心、耐心";
            //操作（写）：字符串->字节数组（编码的过程）
            //方式一
//            char[] c = str.toCharArray();
//            os.write(c,0,c.length);
            //方式二
//            os.write(str);

            //方式三
            os.append(str).append("我是后来居上");
            os.newLine();//换行
            os.append("hello everybody");


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
