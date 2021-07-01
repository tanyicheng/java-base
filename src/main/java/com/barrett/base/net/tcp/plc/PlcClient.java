package com.barrett.base.net.tcp.plc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * 某设备的上位机-客户端
 * @author created by barrett in 2021/6/18 21:07
 **/
public class PlcClient {

    public static void main(String[] args) {
        new PlcClient().client();
    }

    public String address = "";
    public void client() {
        System.out.println("---客户端启动---");
        Socket socket = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            //建立连接
            socket = new Socket("172.16.40.253", 10088);
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


    void test(){

    }
}
