package com.barrett.base.io.io2;

import java.io.*;

/**
 * 数据流
 * 1、写出后读取
 * 2、读取顺序和写出保持一致
 *
 * @Author created by barrett in 2020/5/17 16:15
 */
public class DataStreamDemo {
    public static void main(String[] args) {
        try {
            test1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void test1() throws IOException {
        //写出
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        //操作
        dos.writeUTF("从入门到放弃");
        dos.writeChar('A');
        dos.writeDouble(3);

        dos.flush();
        final byte[] bytes = bos.toByteArray();

        //读取
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
//        TODO 注意；取数的顺序要一致
        final String s = dis.readUTF();
        final char c = dis.readChar();
        final double v = dis.readDouble();


    }
}
