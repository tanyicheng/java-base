package com.barrett.base.net.tcp.socketDemo.demo5;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketDemo {
    public static void main(String[] args) {
        client();
    }

    public static void client() {

        try {
            InetAddress addr = InetAddress.getByName("172.16.2.151");
            Socket so = new Socket(addr,6800);
            so.setSoTimeout(3000);

            System.out.println("发起连接");
            System.out.println("客户端ip: " + so.getLocalAddress() + "   port: " + so.getLocalPort());
            System.out.println("服务端ip: " + so.getInetAddress() + "   port: " + so.getPort());

            connect(so);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 控制台输入
     * @Author created by barrett in 2020/5/24 14:28
     */
    public static void connect(Socket socket) {
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        PrintStream printStream = null;
        BufferedReader bufferedReader = null;
        try {
            OutputStream outputStream = socket.getOutputStream();
            printStream = new PrintStream(outputStream);
            InputStream inputStream = socket.getInputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


            boolean flag = true;
            do{
                String str = input.readLine();
                //发送
                printStream.println(str);

//                String result = bufferedReader.readLine();
//                if("bye".equals(result)){
//                    flag=false;
//                }
            }while (flag);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (printStream != null)
                    printStream.close();
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


}
