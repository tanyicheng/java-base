package com.barrett.base.net.tcp.demo5;

import com.barrett.beans.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 客户端
 *
 * @Author created by barrett in 2020/5/26 22:07
 */
public class Client {
    private static Logger log = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        new Client().client();
    }


    public void client() {
        log.info("---客户端启动---");
        Socket socket;
        try {
            //建立连接
            socket = new Socket("localhost", 8888);
            socket.setSoTimeout(10000);

            //发送消息
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            boolean flag = true;
            int i = 0;

//            new Thread(new Send(socket)).start();
//            new Thread(new Receive(socket)).start();

            //加入循环，收发多次
            while (socket.isConnected()) {

//                Thread.sleep(1000);
                //获取消息
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String msg = reader.readLine();

//                if(msg.equals("10")){
//                    for (int j = 0; j < 10; j++) {
//                        dos.writeUTF(msg+" client forj=" + j);
//                    }
//                }else{
//                    dos.writeUTF(" client say "+msg +" "+ i);
//                }

                //todo 把接收消息用多线程读取会出现拆包，粘包现象

//                Thread.sleep(20*1000);
                dos.writeUTF(" client auto " + msg);
                dos.flush();

                //接收消息
//                String data = dis.readUTF();
//                log.info("1----" + data);
//                data = dis.readUTF();
//                log.info("2----"+data);

                i++;
            }
            //释放资源
            dis.close();
            dos.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            client();
        }

    }

    public void clientObject() {
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
