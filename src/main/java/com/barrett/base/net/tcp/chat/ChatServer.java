package com.barrett.base.net.tcp.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室
 *
 * @Author created by barrett in 2020/5/26 22:07
 */
public class Chat {

    public static void main(String[] args) {
        new Chat().chat();
    }

    public void chat() {
        System.out.println("---服务端启动---");
        try {
            //创建服务器
            ServerSocket server = new ServerSocket(8888);
            //阻塞式等待连接
            Socket accept = server.accept();
            System.out.println("一个客户端连接");

            //接收客户端数据
            DataInputStream dis = new DataInputStream(accept.getInputStream());
            String data = dis.readUTF();
            System.out.println("接收到消息：" + data);

            //返回消息给客户端
             DataOutputStream dos = new DataOutputStream(accept.getOutputStream());
             dos.writeUTF("来自服务端");
             dos.flush();

            //关闭资源
            dos.close();
            dis.close();
            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
