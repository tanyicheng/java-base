package com.barrett.base.io.bio.demo1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.barrett.base.io.bio.demo1.Utils.*;

public class BIODemo1 {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(PORT, BACK_LOG, null);
        System.out.println("服务启动");
        for (; ; ) {
            Socket socket = serverSocket.accept();
            System.out.println(socket.getRemoteSocketAddress());
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bw = buildBuffereWriter(outputStream);
            doSomeWork();
            bw.write(buildHttpResp());
            bw.flush();
        }
    }
}
