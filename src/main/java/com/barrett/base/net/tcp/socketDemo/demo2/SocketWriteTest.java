package com.barrett.base.net.tcp.socketDemo.demo2;

import java.io.IOException;

import java.net.ServerSocket;

import java.net.Socket;

import java.net.UnknownHostException;


import org.junit.Test;

/**
 * TODO 案例参考链接 https://www.iteye.com/blog/moor212-1165414
 * timeout 深入解析 https://blog.csdn.net/usagoole/article/details/82586369
 * tcp粘包拆包 https://www.cnblogs.com/sunliyuan/p/10930124.html  https://blog.csdn.net/m0_37739193/article/details/78738253
 *
 * 1.1 socket套接字缓冲区
 * Java提供了便捷的网络编程模式，尤其在套接字中，直接提供了与网络进行沟通的输入和输出流，用户对网络的操作就如同对文件操作一样简便。在客户端与服务端建立Socket连接后，
 * 客户端与服务端间的写入和写出流也同时被建立，此时即可向流中写入数据，也可以从流中读取数据。在对数据流进行操作时，很多人都会误以为，客户端和服务端的read和write应当是对应的，
 * 即：客户端调用一次写入，服务端必然调用了一次写出，而且写入和写出的字节数应当是对应的。为了解释上面的误解，我们提供了Demo-1的示例。
 * 在Demo-1中服务端先向客户端输出了两次，之后刷新了输出缓冲区。客户端先向服务端输出了一次，然后刷新输出缓冲，之后调用了一次接收操作。
 * 从Demo-1源码以及后面提供的可能出现的结果可以看出，服务端和客户端的输入和输出并不是对应的，有时一次接收操作可以接收对方几次发过来的信息，
 * 并且不是每次输出操作对方都需要接收处理。当然了Demo-1的代码是一种错误的编写方式，没有任何一个程序员希望编写这样的代码。
 *
 * Demo-1
 * @author created by barrett in 2021/8/3 13:12
 **/
public class SocketWriteTest {

    public static final int PORT = 12123;
    public static final int BUFFER_SIZE = 1;

    //服务端代码
    @Test
    public void server() throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(PORT);
        while (true) {
            Socket s = ss.accept();
            //这里向网络进行两次写入
            s.getOutputStream().write("hello ".getBytes());
            s.getOutputStream().write("second ".getBytes());
            s.getOutputStream().flush();
            s.close();

        }

    }


    //客户端代码
    @Test
    public void client() throws UnknownHostException, IOException {

        byte[] buffer;
        Socket s = new Socket("localhost", PORT);//创建socket连接
        s.getOutputStream().write(new byte[BUFFER_SIZE]);
        s.getOutputStream().flush();
        int i = s.getInputStream().read(buffer = new byte[BUFFER_SIZE]);
        System.out.println(new String(buffer, 0, i));

    }

}
