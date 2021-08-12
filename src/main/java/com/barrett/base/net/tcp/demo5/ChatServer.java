package com.barrett.base.net.tcp.demo5;

import com.barrett.beans.Person;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 对象传递，只能从client 传递对象，server接收对象
 * server发送字符，client 接收字符
 * 还无法实现 接收发送都通过对象实现
 *
 * @author created by barrett in 2021/6/24 13:59
 **/
public class ChatServer {

    public static void main(String[] args) {
        new ChatServer().createServer();

    }

    public void createServer() {
        System.out.println("---服务端启动---");
        ServerSocket server = null;
        try {
            //创建服务器
            server = new ServerSocket(7777);

//            Thread.sleep(20*1000);

            while (true) {
                //阻塞式等待连接
                Socket accept = server.accept();
                System.out.println("一个客户端连接 " + accept.getInetAddress() + ":" + accept.getPort());

                new Thread(new SocketChannel(accept)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (server != null) {
                try {
                    server.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            //
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println("start reconnect ...");
            createServer();
        }
    }

    public void create() {
        System.out.println("---服务端启动---");
        try {
            //创建服务器
            ServerSocket server = new ServerSocket(8888);

            //加入多线程
            while (true) {
                //阻塞式等待连接
                Socket accept = server.accept();
                System.out.println("一个客户端连接 " + accept.getInetAddress() + ":" + accept.getPort());

                new Thread(() -> {
                    //接收客户端数据
                    try {

                        DataInputStream dis = new DataInputStream(accept.getInputStream());
                        DataOutputStream dos = new DataOutputStream(accept.getOutputStream());

                        //使用对象
                        ObjectInputStream ois = new ObjectInputStream(accept.getInputStream());

                        boolean flag = true;
                        //加入循环，收发多次
                        while (flag) {
                            try {
                                //readObject()方法必须保证服务端和客户端的User包名一致，要不然会出现找不到类的错误
                                Person person = (Person) ois.readObject();
                                System.out.println("客户端发送的对象：" + person.getName());

//                                String data = null;
//                                data = dis.readUTF();
//                                System.out.println("接收到消息：" + data);
                                //返回消息给客户端
                                dos.writeUTF("来自服务端");
                                dos.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                                //抛出异常停止循环
                                flag = false;
                            }
                        }

                        //关闭资源
                        if (ois != null)
                            ois.close();
                        if (dos != null)
                            dos.close();
                        if (dis != null)
                            dis.close();
                        if (server != null)
                            server.close();
                    } catch (IOException | ClassNotFoundException e) {
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
            create();
        }
    }

}
