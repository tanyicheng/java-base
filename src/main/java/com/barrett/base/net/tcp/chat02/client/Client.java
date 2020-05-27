package com.barrett.base.net.tcp.chat02.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 客户端
 *
 * @Author created by barrett in 2020/5/26 22:07
 */
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
            //发送消息
            new Thread(new Send(socket,name)).start();
            //接收消息
            new Thread(new Receive(socket)).start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
