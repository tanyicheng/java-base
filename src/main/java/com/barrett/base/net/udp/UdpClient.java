package com.barrett.base.net.udp;

import com.barrett.base.io.io1.ByteArrayTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * 客户端(发送端)
 * 1、使用DatagramSocket 指定端口创建发送端
 * 2、准备数据，字节数组格式
 * 3、封装成DatagramPacket 包裹，需要指定目的地
 * 4、发送包裹 send(DatagramSocket p)
 * 5、释放资源
 *
 * @Author created by barrett in 2020/5/20 10:16
 */
public class UdpClient {

    public static void main(String[] args) {
        clientTalk();
    }

    //1对1聊天
    static void clientTalk() {
        try {
            System.out.println("客户端启动，准备发送。。。");
            //* 1、使用DatagramSocket 指定端口创建发送端
            DatagramSocket client = new DatagramSocket(8888);

            //* 2、准备数据，字节数组格式 todo 注意文件不能太大，64kb(未验证)
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             while (true){

                 String data = reader.readLine();
                 byte[] bytes = data.getBytes();

                 //* 3、封装成DatagramPacket 包裹，需要指定目的地
                 DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length,
                         new InetSocketAddress("localhost", 9999));

                 //* 4、发送包裹 send(DatagramSocket p)
                 client.send(packet);
                 if("exit".equals(data)){
                     break;
                 }
             }
            //* 5、释放资源
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //文件操作
    static void client2() {
        try {
            System.out.println("客户端启动，准备发送。。。");
            //* 1、使用DatagramSocket 指定端口创建发送端
            DatagramSocket client = new DatagramSocket(8888);

            //* 2、准备数据，字节数组格式 todo 注意文件不能太大，64kb(未验证)
            byte[] bytes = ByteArrayTest.fileToByteArray("src/main/resources/img/man.gif");

            //* 3、封装成DatagramPacket 包裹，需要指定目的地
            DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length,
                    new InetSocketAddress("localhost", 9999));

            //* 4、发送包裹 send(DatagramSocket p)
            client.send(packet);
            //* 5、释放资源
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //基础操作
    static void client() {
        try {
            System.out.println("客户端启动，准备发送。。。");
            //* 1、使用DatagramSocket 指定端口创建发送端
            DatagramSocket client = new DatagramSocket(8888);

            //* 2、准备数据，字节数组格式
            String data = "我是客户端21";
            byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
            //* 3、封装成DatagramPacket 包裹，需要指定目的地
            DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length,
                    new InetSocketAddress("localhost", 9999));
            //* 4、发送包裹 send(DatagramSocket p)
            client.send(packet);
            //* 5、释放资源
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
