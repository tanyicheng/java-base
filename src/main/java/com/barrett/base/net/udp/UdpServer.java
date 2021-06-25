package com.barrett.base.net.udp;

import com.barrett.base.io.io1.ByteArrayTest;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 服务端（接收消息）
 * 1、使用 DatagramSocket 指定端口创建接收端
 * 2、准备数据，封装DatagramPacket包裹
 * 3、阻塞式接收包裹 receive(DatagramPacket p)
 * 4、分析数据（拆包）
 * byte[] getData()
 * getLength()
 * 5、释放资源
 *
 * @Author created by barrett in 2020/5/20 10:16
 */
public class UdpServer {

    public static void main(String[] args) {
        serverTalk();
    }

    //1v1聊天
    static void serverTalk() {
        try {
            System.out.println("客户端启动，准备接收...");
            //* 1、使用 DatagramSocket 指定端口创建接收端
            DatagramSocket server = new DatagramSocket(9999);

            while (true) {
                //* 2、准备数据，封装DatagramPacket包裹
                final byte[] bytes = new byte[1024 * 20];
                final DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
                //* 3、todo 阻塞式接收包裹 receive(DatagramPacket p)
                server.receive(packet);//阻塞式，会等待
                //* 4、分析数据（拆包）
                byte[] data = packet.getData();
                String s = new String(data, 0, packet.getLength());
                System.out.println(s);
                if("exit".equals(data)){
                    break;
                }
            }
            //释放资源
            server.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //文件操作
    static void server2() {
        try {
            System.out.println("客户端启动，准备接收...");
            //* 1、使用 DatagramSocket 指定端口创建接收端
            DatagramSocket server = new DatagramSocket(9999);
            //* 2、准备数据，封装DatagramPacket包裹
            final byte[] bytes = new byte[1024*20];
            final DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
            //* 3、todo 阻塞式接收包裹 receive(DatagramPacket p)
            server.receive(packet);//阻塞式，会等待
            //* 4、分析数据（拆包）
            byte[] data = packet.getData();

            ByteArrayTest.byteArrayToFile(data,"man-udp-copy.gif");
            //释放资源
            server.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //基础操作
    static void server() {
        try {
            System.out.println("客户端启动，准备接收...");
            //* 1、使用 DatagramSocket 指定端口创建接收端
            DatagramSocket server = new DatagramSocket(9999);
            //* 2、准备数据，封装DatagramPacket包裹
            final byte[] bytes = new byte[1024];
            final DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
            //* 3、todo 阻塞式接收包裹 receive(DatagramPacket p)
            server.receive(packet);//阻塞式，会等待
            //* 4、分析数据（拆包）
            byte[] data = packet.getData();
            String s = new String(data, 0, packet.getLength());
            System.out.println(s);

            //释放资源
            server.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
