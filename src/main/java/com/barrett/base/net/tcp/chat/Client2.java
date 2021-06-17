package com.barrett.base.net.tcp.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 客户端
 *
 * @Author created by barrett in 2020/5/26 22:07
 */
public class Client2 {

    public static void main(String[] args) {
        new Client2().client();
    }

    public String address = "";
    public void client() {
        System.out.println("---客户端启动---");
        Socket socket = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            //建立连接
            socket = new Socket("localhost", 8886);
            //发送消息
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());

            boolean flag = true;
            //加入循环，收发多次
            while (flag) {
                //获取消息
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String msg = reader.readLine();//输入寄存器地址
                dos.writeUTF(msg);
                dos.flush();
                //接收消息
                String data = dis.readUTF();
                System.out.println("接收服务端消息->   " + data);

            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                //出现异常关闭客户端，重新连接
                if (e.getMessage().contains("Connection reset by peer")) {
                    socket.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } finally {
            //释放资源
            try {
                dis.close();
                dos.close();
                socket.close();
                if (socket.isClosed()) {
                    Thread.sleep(3000);
                    System.out.println("client restart");
                    //fixme-1 重新连接是否需要把上次未获取到的信息再次发送？
                    client();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
