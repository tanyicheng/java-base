package com.barrett.base.net.tcp.demo.demo1;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8888,200);
//        Thread.sleep(10000);

        while (true){
            serverSocket.accept();

        }
    }


    //客户端程序
    @Test
    public void testReadTimeOut() {
        Socket socket = new Socket();
        long startTime = 0;
        try {
            socket.connect(new InetSocketAddress("127.0.0.1", 8888), 10000);
            System.out.println("socket连接成功....");
            socket.setSoTimeout(2000);
            startTime = System.currentTimeMillis();
            int read = socket.getInputStream().read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
        }

    }
}
