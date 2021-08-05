package com.barrett.base.net.tcp.demo.demo4;

import java.io.DataInputStream;

import java.io.DataOutputStream;

import java.io.IOException;

import java.net.ServerSocket;

import java.net.Socket;

import java.net.UnknownHostException;


import org.junit.Test;

/**
 * 在Demo-3中的这种消息处理方式过于复杂，需要理解java底层的缓冲区的知识，还需要编程人员完成消息的组合（在消息末尾添加-1），
 * 在Java中可以使用一种简单的方式完成上述的操作，就是使用java DataInputStream和DataOutputStream提供的方法。
 * Demo-4给出了使用java相关流类完成同步的消息的方法（估计他们与我们Demo-3使用的方式是相似的）。你可以查阅java其它API，可以找到其他的方式。
 * 简单总结：
 * <p>
 * 上面主要介绍了java Socket通信的缓冲区机制，并通过几个示例让您对java Socket的工作原理有了简单了解。这里需要注意的是可读状态和可写状态，
 * 因为这两个概念将对下一节的内容理解至关重要。下一节将描述java NIO提高服务端的并发性。
 *
 * Demo-4
 * @author created by barrett in 2021/8/5 21:42
 **/
public class SocketDataStream {

    public static final int PORT = 12123;

    @Test
    public void server() throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        while (true) {
            Socket s = ss.accept();
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF("hello guan xin quan ! ");
            out.writeUTF("let's study java togethor! ");
            System.out.println(in.readUTF());
            s.close();
        }
    }

    @Test
    public void client() throws UnknownHostException, IOException {
        Socket s = new Socket("localhost", PORT);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        System.out.println(in.readUTF());
        System.out.println(in.readUTF());
        out.writeUTF("welcome to java net world ! ");
        s.close();

    }
}