package com.barrett.base.net.tcp.demo.demo3;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.net.ServerSocket;

import java.net.Socket;

import java.net.UnknownHostException;

import java.nio.ByteBuffer;

import java.util.ArrayList;

import java.util.List;


import org.junit.Test;

/**
 * 了解了Socket缓冲区的概念后，需要探讨一下Socket的可写状态和可读状态。当输出缓冲区未满时，Socket是可写的（注意，不是对方启用接收操作后，本地才能可写，这是错误的理解），
 * 因此，当套接字被建立时，即处于可写如的状态。对于可读，则是指缓冲区中有接收到的数据，并且这些数据未完成处理。在socket创建时，并不处于可读状态，
 * 仅当连接的另一方向本套接字的通道写入数据后，本套接字方能处于可读状态（注意，如果对方套接字已经关闭，那么本地套接字将处于可读状态，并且每次调用read后，返回的都是-1）。
 *
 * 现在应用前面的讨论，重新分析一下Demo-1的执行流程，服务端与客户端建立连接后，服务器端先向缓冲区写入两条信息，在第一条信息写入时，缓冲区并未写满，
 * 因此在第二条信息输入时，第一条信息很可能还未发送，因此两条信息可能同时被传送到客户端。另一方面，如果在第二条信息写入时，第一条已经发送出去，
 * 那么客户端的接收操作仅会获得第一条信息，因为客户端没有继续接收的操作，因此第二条信息在缓冲区中，将不会被读取，当socket关闭时，缓冲区将被释放，未被读取的数据也就变的无效了。
 * 如果对方的socket已经关闭，本地再次调用读取方法，则读取方法直接返回-1，表示读到了文件的尾部。
 *
 * 对于缓冲区空间的设定，要根据具体情况来定，如果存在大量的长信息（比如文件传输），将缓冲区定义的大些，可能更好的利用网络资源，如果更多的是短信息（比如聊天消息），
 * 使用小的缓冲区可能更好些，这样刷新的速度会更快。一般系统默认的缓冲大小是8*1024。除非对自己处理的情况很清晰，否则请不要随意更改这个设置。
 *
 * 由于可读状态是在对方写入数据后或socket关闭时才能出现，因此如果客户端和服务端都停留在read时，如果没有任何一方，向对方写入数据，这将会产生一个死锁。
 *
 * 此外，在本地接收操作发起之前，很可能接收缓冲区中已经有数据了，这是一种异步。不要误以为，本地调用接收操作后，对方才会发送数据，实际数据何时到达，本地不能做出任何假设。
 * 如果想要将多条输入的信息区分开，可以使用一些技巧，在文件操作中使用-1表示EOF，就是文件的结束，在网络传输中，也可以使用-1表示一条传输语句的结束。Demo-3中给出了一个读取和写入操作，
 * 在客户端和服务端对称的使用这两个类，可以将每一条信息分析出来。Demo-3中并不是将网络的传输同步，而是分析出缓冲中的数据，将以-1为结尾进行数据划分。如果写聊天程序可以使用类似的模式。
 * <p>
 * Demo-3
 *
 * @author created by barrett in 2021/8/5 21:39
 **/
public class SocketWriteTest {

    public static final int PORT = 12123;
    public static final int BUFFER_SIZE = 1024;

    //读取一条传入的，以-1为结尾的数据
    public class ReadDatas {
        //数据临时缓冲用
        private List<ByteBuffer> buffers = new ArrayList<ByteBuffer>();
        private Socket socket;//数据的来源

        public ReadDatas(Socket socket) throws IOException {
            this.socket = socket;
        }

        public void read() throws IOException {
            buffers.clear();//清空上次的读取状态
            InputStream in = socket.getInputStream();//获取输入流
            int k = 0;
            byte r = 0;
            while (true) {
                ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);//新分配一段数据区
                //如果新数据区未满，并且没有读到-1，则继续读取
                for (k = 0; k < BUFFER_SIZE; k++) {
                    r = (byte) in.read();//读取一个数据
                    //数据不为-1，简单放入缓冲区
                    if (r != -1) {
                        buffer.put(r);
                    } else {//读取了一个-1，表示这条信息结束
                        buffer.flip();//翻转缓冲，以备读取操作
                        buffers.add(buffer);//将当前的buffer添加到缓冲列表
                        return;
                    }
                }
                buffers.add(buffer);//由于缓冲不足，直接将填满的缓冲放入缓冲列表
            }
        }

        public String getAsString() {
            StringBuffer str = new StringBuffer();
            for (ByteBuffer buffer : buffers)//遍历缓冲列表
            {
                str.append(new String(buffer.array(), 0, buffer.limit()));//组织字符串
            }
            return str.toString();//返回生成的字符串
        }
    }


    //将一条信息写出给接收端
    public class WriteDatas {
        public Socket socket;//数据接收端

        public WriteDatas(Socket socket, ByteBuffer[] buffers) throws IOException {
            this.socket = socket;
            write(buffers);
        }

        public WriteDatas(Socket socket) {
            this.socket = socket;
        }


        public void write(ByteBuffer[] buffers) throws IOException {
            OutputStream out = socket.getOutputStream();//获取输出流
            for (ByteBuffer buffer : buffers) {
                out.write(buffer.array());//将数据输出到缓冲区
            }
            out.write(new byte[]{-1});//输出终结符
            out.flush();//刷新缓冲区
        }
    }

    //服务端代码
    @Test
    public void server() throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(PORT);
        while (true) {
            //阻塞式等待
            Socket s = ss.accept();

            //从网络连续读取两条信息
            ReadDatas read = new ReadDatas(s);
            read.read();
            System.out.println(read.getAsString());
            read.read();
            System.out.println(read.getAsString());
            //向网络中输出一条信息
            WriteDatas write = new WriteDatas(s);
            write.write(new ByteBuffer[]{ByteBuffer.wrap("welcome to us ! ".getBytes())});
            //关闭套接字
            s.close();
        }
    }

    //客户端代码
    @Test
    public void client() throws UnknownHostException, IOException {
        Socket s = new Socket("localhost", PORT);//创建socket连接
        //连续向服务端写入两条信息
        WriteDatas write = new WriteDatas(s, new ByteBuffer[]{ByteBuffer.wrap("ni hao guan xin quan ! ".getBytes())});
        write.write(new ByteBuffer[]{ByteBuffer.wrap("let's study java network !".getBytes())});
        //从服务端读取一条信息
        ReadDatas read = new ReadDatas(s);
        read.read();
        System.out.println(read.getAsString());
        //关闭套接字
        s.close();
    }
}
