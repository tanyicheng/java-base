package com.barrett.base.net.tcp.demo5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketChannel implements Runnable {
    private static Logger log = LoggerFactory.getLogger(SocketChannel.class);

    private Socket socket;

    public SocketChannel(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //接收客户端数据
        try {

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            boolean flag = true;
            //加入循环，收发多次
            while (flag) {
                try {
                    String data = dis.readUTF();
                    log.info(data);
//                    Thread.sleep(20*1000);
                    //返回消息给客户端
                    for (int i = 0; i < 2; i++) {
                        //这里写完，客户端就能读取到数据
                        dos.writeUTF(i+".server; ");
                    }
                    //fixme-1 这里的作用是什么
                    dos.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    //抛出异常停止循环
                    flag = false;
                }
            }

            //关闭资源
            dos.close();
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
