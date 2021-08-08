package com.barrett.base.net.tcp.异步通讯;

import java.io.IOException;

import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 13579);
            socket.setSoTimeout(1000);
            new Thread(new ChatThread(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}