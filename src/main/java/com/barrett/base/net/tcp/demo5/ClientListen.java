package com.barrett.base.net.tcp.demo5;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.barrett.base.net.tcp.IOUtils;
import com.barrett.util.getId.Sequence;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientListen extends Base5 implements Runnable {

    private static final Log log = LogFactory.getLog(ClientListen.class);

    //plc-client 所部属服务器的ip
    private String ip = "localhost";
    private int port;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    //判断通讯是否连接
    private boolean flag;
    //是否需要重新连接，用于监听断开后重莲
    private boolean reconnect;

    private static volatile ClientListen instance;

    //注意：同一个客户端只能提供一个对象
    public static ClientListen getInstance(int port) {
        if (instance == null) {
            synchronized (ClientListen.class) {
                if (instance == null) {
                    instance = new ClientListen(port);
                }
            }
        }
        return instance;
    }

    //获取实例，不会新建
    public static ClientListen getInstance() {
        return instance;
    }

    private ClientListen(int port) {
        this.port = port;
        this.reconnect = true;
    }

    public void init(Socket socket) {
        try {

//            socket.setSendBufferSize(1024 * 32);
//            socket.setReceiveBufferSize(1024 * 32);
//            //输出缓冲区大小
//            System.out.println(socket.getSendBufferSize());
//            System.out.println(socket.getReceiveBufferSize());

            this.socket = socket;
            this.flag = true;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            socket.setSoTimeout(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        clientConnect();
    }

    private void clientConnect() {
        log.info("准备连接：" + ip + ":" + port);
        try {
            if (reconnect && (socket == null || socket.isClosed())) {
                //建立连接
                socket = new Socket(ip, port);
                if (socket.isBound()) {
                    log.info(port + " 排版机连接成功，监听中...");
                    init(socket);
                    //发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
                    socket.sendUrgentData(0xFF);

//                    new Thread(new Receive(socket)).start();

//                    listenPbj();


                }
            } else {
                log.info("【排版机玻璃到位】socket存在，不自动连接");
                listenPbj();
            }

        } catch (Exception e) {
            e.printStackTrace();

            log.info("socket 通讯发生异常，等待重连..." + port);
            sleep(10);
            clientConnect();
        }
    }

    public void listenPbj() {
        int a = 0;
        while (flag) {
            send("testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest " + a++);
//            String receive = receive();
//            log.info("接收消息：" + receive);
        }
    }


    //接收消息
    private String receive() {
        try {
            return dis.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            //有异常则释放
            release();
        }
        return null;
    }

    //发送消息
    private void send(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            //有异常则释放
            release();
        }
    }


    //释放消息
    private void release() {
        log.info("连接终止---" + socket.getLocalPort());
        IOUtils.close(dos, dis, socket);
        this.flag = false;
    }
}
