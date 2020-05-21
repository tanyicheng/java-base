package com.barrett.base.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 消息接收
 *
 * @Author created by barrett in 2020/5/21 22:34
 */
public class TalkReceive implements Runnable {

    private DatagramSocket server;

    public TalkReceive(int port) {
        try {
            server = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true) {
            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
            String str = null;
            try {
                //阻塞式等待接收消息
                server.receive(packet);
                byte[] data = packet.getData();
                str = new String(data, 0, packet.getLength());
                System.out.println(str);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if ("exit".equals(str)) {
                break;
            }
        }
        //释放资源
        server.close();
    }
}
