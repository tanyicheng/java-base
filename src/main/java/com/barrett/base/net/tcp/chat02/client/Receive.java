package com.barrett.base.net.tcp.chat02.client;

import com.barrett.base.net.tcp.IOUtils;
import com.sun.xml.internal.ws.util.StringUtils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 接收消息
 * @Author created by barrett in 2020/5/27 21:55
 */
public class Receive implements Runnable{

    private DataInputStream dis;
    private Socket socket;
    private boolean flag;

    public Receive(Socket socket) {
        try {
            this.flag = true;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
    }

    @Override
    public void run() {
        while (flag){
             String msg = receive();
             if(msg != null) {
                 System.out.println(msg);
             }
        }
    }

    //接收消息
    private String receive(){
        String msg = "";
        try {
            msg= dis.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            //有异常则释放
            release();
        }
        return msg;
    }

    //释放消息
    private void release(){
        IOUtils.close(dis,socket);
    }

}
