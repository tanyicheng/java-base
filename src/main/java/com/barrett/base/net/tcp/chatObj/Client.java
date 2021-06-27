package com.barrett.base.net.tcp.chatObj;

import com.barrett.beans.Person;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 客户端
 *
 * @Author created by barrett in 2020/5/26 22:07
 */
public class Client {

    public static void main(String[] args) {
        new Client().client();
    }

    public void client() {
        System.out.println("---客户端启动---");
        Socket socket;
        try {
            //建立连接
            socket = new Socket("localhost", 8888);

            //发送消息
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            //对象传输
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //fixme-1 直接会卡死
//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            boolean flag = true;
            //加入循环，收发多次
            while (flag) {
                //获取消息
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String msg = reader.readLine();
                oos.writeObject(new Person(1, msg, "123456"));

//                dos.writeUTF(msg+"---------------------------");
//                dos.flush();
                oos.flush();

                //接收消息
                String data = dis.readUTF();
                System.out.println("接收服务端消息：" + data);
//                Person person = (Person) ois.readObject();
//                System.out.println("接收服务端对象：" + person.toString());

            }
            //释放资源
            oos.close();
            dis.close();
            dos.close();
//            ois.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
