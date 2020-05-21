package com.barrett.base.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
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
        client();
    }

    static void client() {
        try {
            System.out.println("客户端启动，准备发送。。。");
            //* 1、使用DatagramSocket 指定端口创建发送端
            DatagramSocket client = new DatagramSocket(8888);

            //* 2、准备数据，字节数组格式
            String data = "我是客户端21";
            byte[] bytes = data.getBytes(StandardCharsets.UTF_8 );
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
