package com.barrett.base.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

/**
 * 服务端（接收消息）
 * 1、使用 DatagramSocket 指定端口创建接收端
 * 2、准备数据，封装DatagramPacket包裹
 * 3、阻塞式接收包裹 receive(DatagramPacket p)
 * 4、分析数据（拆包）
 *      byte[] getData()
 *             getLength()
 * 5、释放资源
 * @Author created by barrett in 2020/5/20 10:16
 */
public class UdpServer {

    public static void main(String[] args) {
        server();
    }

    static void server(){
        try {
            System.out.println("客户端启动，准备接收...");
            //* 1、使用 DatagramSocket 指定端口创建接收端
            DatagramSocket server = new DatagramSocket(9999);
            //* 2、准备数据，封装DatagramPacket包裹
            final byte[] bytes = new byte[1024];
            final DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
            //* 3、阻塞式接收包裹 receive(DatagramPacket p)
            server.receive(packet);//阻塞式，会等待
            //* 4、分析数据（拆包）
            final byte[] data = packet.getData();
            final String s = new String(data, 0, data.length, StandardCharsets.UTF_8 );
            System.out.println(s);
            //释放资源
            server.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
