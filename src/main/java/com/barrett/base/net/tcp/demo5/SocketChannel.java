package com.barrett.base.net.tcp.demo5;

import com.barrett.base.net.tcp.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketChannel extends Base5 implements Runnable {

    private Socket socket;
    private boolean flag;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    public SocketChannel(Socket socket) throws IOException {
        this.socket = socket;
        this.dis = new DataInputStream(socket.getInputStream());
        this.dos = new DataOutputStream(socket.getOutputStream());
        this.flag = true;
    }

    @Override
    public void run() {
        createServer();
    }

    public void createServer() {
        //加入循环，收发多次
        while (flag) {
            try {
                //接收客户端数据 test=220131 test...=27619
                String data = receive();
                log.info("来自客户端: " + data);
//                Thread.sleep(1000);
                if (StringUtils.isNotEmpty(data)) {
                    send(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            //关闭资源
            dos.close();
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //接收消息
    private String receive() {
        try {
            return dis.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            //有异常则释放
            release();
        }
        return null;
    }

    //发送消息
    private void send(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            //有异常则释放
            release();
        }
    }

    //释放消息
    private void release() {
        log.info("连接终止---" + socket.getLocalPort());
        IOUtils.close(dos, dis, socket);
        flag = false;
    }
}
