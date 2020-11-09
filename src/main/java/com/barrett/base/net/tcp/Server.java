package com.barrett.base.net.tcp;


import com.barrett.common.constants.Constants;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Channel;

/**
 * 指定端口 使用serverSocket 创建服务器
 * 阻塞式等待连接 accept
 * @Author created by barrett in 2020/5/24 14:17
 */
public class Server {

    public static void main(String[] args) {
//        new Server().serverMulit();
        new Server().server();
    }

    public void serverFile(){
        try {
            //1、指定端口 使用serverSocket 创建服务器
            ServerSocket server = new ServerSocket(9999);
            //2、阻塞式等待连接 accept
            Socket socket = server.accept();
            System.out.println("一个客户端连接。。。");

            //3、输入输出流操作
            InputStream is = new BufferedInputStream(socket.getInputStream());
            OutputStream os = new BufferedOutputStream(new FileOutputStream("src/copy.gif"));
            byte[] bytes = new byte[1024];
            int len=-1;
            while ((len=is.read(bytes))!=-1){
                os.write(bytes,0,len);
            }
            os.flush();

            //4、释放资源
            os.close();
            is.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void server(){
        try {
            //1、指定端口 使用serverSocket 创建服务器
            ServerSocket server = new ServerSocket(9999);
            //2、阻塞式等待连接 accept
            Socket socket = server.accept();
            System.out.println("一个客户端连接。。。");

            //3、输入输出流操作
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String data = dis.readUTF();
            System.out.println(data);

            //4、释放资源
            dis.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理多个客户端
     * @Author created by barrett in 2020/5/25 21:50
     */
    public void serverMulit(){
        try {
            //1、指定端口 使用serverSocket 创建服务器
            ServerSocket server = new ServerSocket(9999);

            //2、阻塞式等待连接 accept
            while(true){
                Socket socket = server.accept();
                System.out.println("一个客户端连接。。。");
                Channel channel = new Channel(socket);
                new Thread(channel).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class Channel implements Runnable{

        private  DataInputStream dis;
        private  DataOutputStream dos;
        private Socket client;

        //通过构造器初始化数据
        public Channel( Socket client) {
            try {
                this.client = client;
                this.dis = new DataInputStream(client.getInputStream());
                this.dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    client.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        //接收数据
        public String resive(){
             String s="";
            try {
                s = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }

        @Override
        public void run() {

            String str = "";

            String data = resive();
            System.out.println(data);

            //写出到客户端
            try {
                dos.writeUTF("ok");
            } catch (IOException e) {
                e.printStackTrace();
            }


            //4、释放资源
            try {
                dos.close();
                dis.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
