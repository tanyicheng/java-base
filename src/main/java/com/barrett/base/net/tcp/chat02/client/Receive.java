package com.barrett.base.net.tcp.chat02.client;

import com.barrett.base.net.tcp.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 接收消息
 * @Author created by barrett in 2020/5/27 21:55
 */
public class Receive implements Runnable{
    private static Logger log = LoggerFactory.getLogger(Receive.class);

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
             if(StringUtils.isNotEmpty(msg)) {
                 log.info(msg);
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
        flag=false;
    }

}
