package com.barrett.base.net.tcp;


import com.barrett.common.constants.Constants;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 指定端口 使用serverSocket 创建服务器
 * 阻塞式等待连接 accept
 * @Author created by barrett in 2020/5/24 14:17
 */
public class Server {

    public static void main(String[] args) {
        serverFile();
    }

    public static void serverFile(){
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
    public static void server(){
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
}
