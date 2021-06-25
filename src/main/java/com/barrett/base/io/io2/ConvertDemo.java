package com.barrett.base.io.io2;

import java.io.*;
import java.net.URL;

/**
 * 转换流：InputStreamReader OutputStreamWriter
 * 1、以字符流的形式操作字节流（纯文本）
 * 2、指定字符集
 *
 * @Author created by barrett in 2020/5/17 15:33
 */
public class ConvertDemo {
    public static void main(String[] args) {
        test2();

        //http://demo.qfpffmp.cn/cssthemes6/sads_4_tyjnb/lib/bootstrap/css/bootstrap.css
    }

    static void test2() {
        try (
                BufferedReader read =
                        new BufferedReader(//提高流处理的速度
                                new InputStreamReader(//字节流转换为字符流
                                        new URL("http://www.baidu.com").openStream(), //节点流
                                        "utf-8"));
                //输出到文件中
                BufferedWriter writer =
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream("baidu.html"), "utf-8"))
        ) {
            String msg = "";
            while ((msg = read.readLine()) != null) {
                writer.write(msg);
                writer.newLine();
//                System.out.print(msg);
            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void test1() {

        //System.in/out 是字节，转换成字符
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
            String msg = "";
            while (!"exit".equals(msg)) {
                msg = reader.readLine();

                writer.write(msg);
                writer.newLine();
                writer.flush();//默认8k才会写出，这里强制刷新

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
