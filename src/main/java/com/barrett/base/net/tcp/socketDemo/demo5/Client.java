package com.barrett.base.net.tcp.socketDemo.demo5;


import com.barrett.common.constants.Constants;

import java.io.*;
import java.net.Socket;

/**
 * 客户端
 *
 * @Author created by barrett in 2020/5/24 14:29
 */
public class Client {
    public static void main(String[] args) {
//        new Client().clientMulit();
        clientPlc();
    }


    public static void clientPlc() {
        try {
            //建立连接
            Socket client = new Socket("127.0.0.1", 9999);
            //输入输出流操作
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            String str = "hello\n123";
            dos.writeBytes(str);
            dos.flush();
            //释放资源
            dos.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //上传文件
    public static void clientFile() {
        try {
            //建立连接
            Socket client = new Socket("127.0.0.1", 9999);
            //输入输出流操作
            InputStream is = new BufferedInputStream(new FileInputStream(Constants.file_path_man));
            BufferedOutputStream bos = new BufferedOutputStream(client.getOutputStream());
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = is.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            bos.flush();

            //释放资源
            bos.close();
            is.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void client() {
        try {
            //建立连接
            Socket client = new Socket("127.0.0.1", 15000);
            //输入输出流操作
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            String str = "hello";
            dos.writeUTF(str);
            dos.flush();
            //释放资源
            dos.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clientMulit() {
        try {
            //通过控制台输入
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //建立连接
            Socket client = new Socket("127.0.0.1", 9999);

            //发送
            String s1 = reader.readLine();
            new Send(client).send(s1);

            //接收服务端消息
            new Receive(client).receive();

            //释放资源
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Send {
        private DataOutputStream dos;
        private Socket client;

        public Send(Socket client) {
            this.client = client;
            try {
                this.dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send(String msg) {

            //输入输出流操作
            try {
                dos = new DataOutputStream(client.getOutputStream());
//                String str = "hello";
                dos.writeUTF(msg);
                dos.flush();
//                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Receive {
        private Socket client;
        private DataInputStream is;

        public Receive(Socket client) {
            this.client = client;
            try {
                this.is = new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void receive() {
            try {
                String s = is.readUTF();
                System.out.println(s);

                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
