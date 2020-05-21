package com.barrett.base.net.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 发送消息
 *
 * @Author created by barrett in 2020/5/21 22:34
 */
public class TalkSend implements Runnable {

    private DatagramSocket client;
    private BufferedReader reader;
    //对方的ip地址
    private String toIP;
    //对方的端口
    private int toPort;

    //客户端端口，服务端ip,服务端端口
    public TalkSend(int port, String toIP, int toPort) {
        this.toIP = toIP;
        this.toPort = toPort;
        try {
            client = new DatagramSocket(port);
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            String data = null;
            try {
                data = reader.readLine();
                byte[] bytes = data.getBytes();
                final DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress(toIP, toPort));
                client.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if ("exit".equals(data)) {
                break;
            }
        }
        //释放资源
        client.close();
    }
}
