package com.barrett.base.net.tcp.plc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 流水线客户端
 * @author created by barrett in 2021/6/18 21:40
 **/
public class Client {

    public static void main(String[] args) {
        new Client().client();
    }

    public void client() {
        System.out.println("---客户端启动---");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名：");
        Socket socket;
        try {
            //建立连接
            socket = new Socket("localhost", 8888);
            String name = reader.readLine();
            System.out.println("启动线程");
            //发送消息
            new Thread(new Send(socket, name)).start();
            //接收消息
            new Thread(new Receive(socket)).start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
