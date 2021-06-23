package com.barrett.base.net.tcp.plc;

import com.barrett.base.net.tcp.IOUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * plc 中心服务
 * @author created by barrett in 2021/6/18 20:35
 **/
public class PlcServer {

    public static void main(String[] args) {
        new PlcServer().server();

    }

    public void server() {
        System.out.println("---服务端启动---");
        try {
            //创建服务器
            ServerSocket server = new ServerSocket(8888);

            //fixme-1 客户端，服务端任意断开可自动重连
            while (true) {
                //阻塞式等待连接
                Socket accept = server.accept();
                System.out.println("一个客户端连接 "+accept.getInetAddress()+":"+accept.getPort());

                new Thread(new Channel(accept)).start();

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
            server();
        }
    }
}

class Channel implements Runnable{

    private Socket socket;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private boolean flag;

    public Channel(Socket socket) {
        this.socket = socket;
        flag = true;
        try {
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (flag) {
            String msg = receive();
            System.out.println("来自客户端：" + msg);
            if (msg != null) {
                int a = (int) (Math.random() * 100);
                send("->"+a);
            }
        }
    }

    //接收消息
    private String receive() {
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            //有异常则释放
            release();
        }
        return msg;
    }

    //发送消息
    private void send(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            //有异常则释放
            release();
        }
    }

    //释放消息
    private void release() {
        IOUtils.close(dos, dis, socket);
        flag=false;
    }
}