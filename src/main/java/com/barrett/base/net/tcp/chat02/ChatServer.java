package com.barrett.base.net.tcp.chat02;

import com.barrett.base.net.tcp.IOUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 在线聊天室
 * 代码封装
 *
 * @Author created by barrett in 2020/5/26 22:07
 */
public class ChatServer {

    //增加容器，实现群聊；推荐这个容器，
    // 因为在多线程环境下，遍历中添加、移除，会导致数据不一致而报错，使用此容器会copy一份进行遍历，不影响添加、移除
    private static CopyOnWriteArrayList<Channel> allList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        new ChatServer().chat();

    }

    public void chat() {
        System.out.println("---服务端启动---");
        try {
            //创建服务器
            ServerSocket server = new ServerSocket(8888);

            while (true) {
                //阻塞式等待连接
                Socket accept = server.accept();
                System.out.println("一个客户端连接");
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
        private String name;

        public Channel(Socket socket) {
            this.socket = socket;
            this.flag = true;
            try {
                this.dis = new DataInputStream(socket.getInputStream());
                this.dos = new DataOutputStream(socket.getOutputStream());

                //获取名称
                this.name = receive();
                //返回给自己
                this.send("欢迎您的加入！");
                //对其他人说
                this.sendOthers("欢迎" + name + "加入聊天室！", true);
            } catch (IOException e) {
                e.printStackTrace();
                //有异常释放资源
                release();
            }
        }

        @Override
        public void run() {
            while (flag) {
                String msg = receive();
                System.out.println("接收消息：" + msg);
                if (msg != null) {
                    sendOthers(msg, false);
                }
            }
        }

        //接收消息
        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (Exception e) {
                e.printStackTrace();
                //有异常则释放
                release();
            }
            return msg;
        }

        //发送消息
        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
                //有异常则释放
                release();
            }

        }

        /**
         * 获取自己的消息发给其他人
         * 或私聊 规定格式  @xxx:
         *
         * @Author created by barrett in 2020/5/27 22:31
         */
        private void sendOthers(String msg, boolean sys) {
            boolean privateChat = msg.startsWith("@");
            //私聊
            if (privateChat) {
                int idx = msg.indexOf(":");
                String tagName = msg.substring(1, idx);
                msg = msg.substring(idx + 1);
                for (Channel other : allList) {
                    //私聊给目标
                    if (tagName.equals(other.name)) {
                        other.send(this.name+" 悄悄对你说：" + msg);
                        break;
                    }
                }

            } else {
                for (Channel other : allList) {
                    //不能发送给自己
                    if (other == this) {
                        continue;
                    } else {
                        //系统消息
                        if (sys) {
                            other.send("系统消息：" + msg);
                        } else {
                            other.send("【" + this.name + "】对所有人说：" + msg);
                        }
                    }
                }
            }

        }

        //释放消息
        private void release() {
            IOUtils.close(dos, dis, socket);
            //有用户退出
            allList.remove(this);
            sendOthers("【" + this.name + "】离线了！", true);
        }

    }
}
