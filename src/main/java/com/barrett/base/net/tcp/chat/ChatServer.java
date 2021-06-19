package com.barrett.base.net.tcp.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 在线聊天室
 *
 * @Author created by barrett in 2020/5/26 22:07
 */
public class ChatServer {

    public static void main(String[] args) {
        new ChatServer().chat();

    }

    public void chat() {
        System.out.println("---服务端启动---");
        try {
            //创建服务器
            ServerSocket server = new ServerSocket(8888);

            //加入多线程
            while (true) {
                //阻塞式等待连接
                Socket accept = server.accept();
                System.out.println("一个客户端连接 "+accept.getInetAddress()+":"+accept.getPort());

                new Thread(() -> {
                    //接收客户端数据
                    try {
                        DataInputStream dis = new DataInputStream(accept.getInputStream());
                        DataOutputStream dos = new DataOutputStream(accept.getOutputStream());

                        boolean flag = true;
                        //加入循环，收发多次
                        while (flag) {
                            try {
                                String data = null;
                                data = dis.readUTF();
                                System.out.println("接收到消息：" + data);
                                //返回消息给客户端
                                dos.writeUTF("来自服务端");
                                dos.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                                //抛出异常停止循环
                                flag=false;
                            }
                        }

                        //关闭资源
                        if (dos != null)
                            dos.close();
                        if (dis != null)
                            dis.close();
                        if (server != null)
                            server.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }
        } catch (Exception e) {
            e.printStackTrace();
            //
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println("start reconnect ...");
            chat();
        }
    }

}
