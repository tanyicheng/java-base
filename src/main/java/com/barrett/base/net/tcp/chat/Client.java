package com.barrett.base.net.tcp.chat;

import java.io.*;
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
        Socket socket;
        try {
            //建立连接 172.16.40.100
            socket = new Socket("localhost", 8888);

            //发送消息
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            boolean flag = true;
//            //加入循环，收发多次
//            while (flag) {
//                //获取消息
//                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//                String msg = reader.readLine();
//
//                dos.writeUTF(msg+"---------------------------");
//                dos.flush();
//                //接收消息
//                String data = dis.readUTF();
//                System.out.println("接收服务端消息" + data);
//            }

            dos.writeUTF("7890");
            dos.flush();

            //释放资源
            dis.close();
            dos.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
