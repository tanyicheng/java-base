package com.barrett.base.net.tcp.demo5;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.barrett.base.net.tcp.IOUtils;
import com.barrett.base.net.tcp.SocketTransmitData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 在线聊天室
 * 代码封装
 *
 * @Author created by barrett in 2020/5/26 22:07
 */
public class ChatServer2 {
    private static final Log log = LogFactory.getLog(ChatServer2.class);

    //增加容器，实现群聊；推荐这个容器，
    // 因为在多线程环境下，遍历中添加、移除，会导致数据不一致而报错，使用此容器会copy一份进行遍历，不影响添加、移除
    private static CopyOnWriteArrayList<Channel> allList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        new ChatServer2().chat();

    }

    public void chat() {
        System.out.println("---服务端启动---");
        try {
            //创建服务器
            ServerSocket server = new ServerSocket(7777);

            while (true) {
                //阻塞式等待连接
                Socket accept = server.accept();
                System.out.println("一个客户端连接" + accept.getLocalPort());
                Channel channel = new Channel(accept);
                allList.add(channel);//管理所有的客户端
                new Thread(channel).start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 封装消息处理
     *
     * @Author created by barrett in 2020/5/27 20:41
     */
    class Channel implements Runnable {
        private Socket socket;
        private DataInputStream dis = null;
        private DataOutputStream dos = null;
        private boolean flag;
        private String source;

        public Channel(Socket socket) {
            this.socket = socket;
            this.flag = true;
            try {
                this.dis = new DataInputStream(socket.getInputStream());
                this.dos = new DataOutputStream(socket.getOutputStream());

                //获取名称
                this.source = receive();
                //返回给自己
                SocketTransmitData data = new SocketTransmitData(true, "10000");
                data.setValue("欢迎您的加入！");
                data.setSource(source);
                this.send(data);

                //对其他人说
//                this.sendOthers("欢迎" + name + "加入聊天室！", true);
            } catch (EOFException eof) {
                //单独捕获这个异常
                log.error("", eof);
            } catch (IOException e) {
                e.printStackTrace();
                //有异常释放资源
                release();
            }
        }

        @Override
        public void run() {
            while (flag) {
                //服务器接收消息
                SocketTransmitData msg = receiveObj();
                System.out.println("接收消息：" + msg.toString());
                //转发给指定客户端
                sendOthers(msg);
                //回复消息给发送端，等待另一台客户端接收成功后发送
//                SocketTransmitData data = new SocketTransmitData(true, "9999");
//                send(data);
            }
        }

        //接收消息
        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (EOFException eof) {
                //单独捕获这个异常
                log.error("", eof);
            } catch (Exception e) {
                e.printStackTrace();
                //有异常则释放
                release();
            }
            return msg;
        }

        public SocketTransmitData receiveObj() {
            try {
                String msg = dis.readUTF();
                SocketTransmitData data = JSONObject.parseObject(msg, SocketTransmitData.class);
                return data;
            } catch (EOFException | SocketTimeoutException eof) {
                //单独捕获这个异常
                log.error("", eof);
            } catch (Exception e) {
                e.printStackTrace();
                //有异常则释放
                release();
            }
            return null;
        }

        //发送消息
        private void send(SocketTransmitData data) {
            try {
                String msg = JSON.toJSONString(data);
                dos.writeUTF(msg);
                dos.flush();
            } catch (EOFException eof) {
                //单独捕获这个异常
                log.error("", eof);
            } catch (IOException e) {
                e.printStackTrace();
                //有异常则释放
                release();
            }
        }

        /**
         * 获取自己的消息发给其他人
         *
         * @Author created by barrett in 2020/5/27 22:31
         */
        private void sendOthers(SocketTransmitData msg) {
            SocketTransmitData data = null;
            if (("1000").equals(msg.getDataType())) {
                data = new SocketTransmitData(true, "9998");
                data.setObject(msg.getObject());

            } else if("1001".equals(msg.getDataType())){
                data = new SocketTransmitData(true, "1001");
                data.setValue(msg.getValue());
            }else{
                //没有指定数据类型
                data = new SocketTransmitData(false, "0000");
            }

            data.setSource(msg.getSource());
            data.setTarget(msg.getTarget());
            //发送消息给另一台
            for (Channel other : allList) {
                //发给目标
                if (msg.getTarget().equals(other.source)) {
                    other.send(data);
                    break;
                }
            }

        }

        //释放消息
        private void release() {
            log.error("释放消息 release");
            IOUtils.close(dos, dis, socket);
            //有用户退出
            allList.remove(this);
            flag = false;
//            sendOthers("【" + this.name + "】离线了！", true);
        }

    }
}
