package com.barrett.base.net.tcp.socketDemo.demo2;

import java.net.Socket;

import java.net.SocketException;

/**
 * socket 缓冲区大小设定
 * 为了深入理解网络发送数据的流程，我们需要对Socket的数据缓冲区有所了解。在创建Socket后，系统会为新创建的套接字分配缓冲区空间。
 * 这时套接字已经具有了输入缓冲区和输出缓冲区。可以通过Demo-2中的方式来获取和设置缓冲区的大小。缓冲区大小需要根据具体情况进行设置，
 * 一般要低于64K（TCP能够指定的最大负重载数据量，TCP的窗口大小是由16bit来确定的），增大缓冲区可以增大网络I/O的性能，而减少缓冲区有助于减少传入数据的backlog（就是缓冲长度，因此提高响应速度）。
 * 对于Socket和SeverSocket如果需要指定缓冲区大小，必须在连接之前完成缓冲区的设定。
 * <p>
 * Demo-2
 *
 * @author created by barrett in 2021/8/3 14:21
 **/
public class SocketBufferTest {
    public static void main(String[] args) throws SocketException {
        //创建一个socket
        Socket socket = new Socket();
        //输出缓冲区大小
        System.out.println(socket.getSendBufferSize());
        System.out.println(socket.getReceiveBufferSize());

        //重置缓冲区大小
        socket.setSendBufferSize(1024 * 32);
        socket.setReceiveBufferSize(1024 * 32);

        //再次输出缓冲区大小
        System.out.println(socket.getSendBufferSize());
        System.out.println(socket.getReceiveBufferSize());
    }

}