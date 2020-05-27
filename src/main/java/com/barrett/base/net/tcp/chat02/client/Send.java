package com.barrett.base.net.tcp.chat02.client;

import com.barrett.base.net.tcp.IOUtils;

import java.io.*;
import java.net.Socket;

/**
 * 发送消息
 *
 * @Author created by barrett in 2020/5/27 21:55
 */
public class Send implements Runnable {
    private BufferedReader console;
    private DataOutputStream dos;
    private Socket socket;
    private boolean flag;
    private String name;

    public Send(Socket socket,String name) {
        try {
            this.flag = true;
            this.name = name;
            this.socket = socket;
            this.console = new BufferedReader(new InputStreamReader(System.in));
            this.dos = new DataOutputStream(socket.getOutputStream());
            //构建完成发送姓名到聊天室
            send(name);
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
    }

    @Override
    public void run() {
        while (flag) {
            String msg = getMsgFromConsole();
            if (null != msg && !"".equals(msg)) {
                send(msg);
            }

        }

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
     * 从控制台获取消息
     *
     * @Author created by barrett in 2020/5/27 22:09
     */
    private String getMsgFromConsole() {
        String msg = null;
        try {
            msg = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

    //释放消息
    private void release() {
        IOUtils.close(dos, socket);
    }
}
