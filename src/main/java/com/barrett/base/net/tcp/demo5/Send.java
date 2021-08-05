package com.barrett.base.net.tcp.demo5;

import com.barrett.base.net.tcp.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public Send(Socket socket) {
        try {
            this.flag = true;
            this.socket = socket;
            this.console = new BufferedReader(new InputStreamReader(System.in));
            this.dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
    }

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            String msg = getMsgFromConsole();
            if (StringUtils.isNotEmpty(msg)) {
                if (msg.equals("10")) {
                    for (int j = 0; j < 10; j++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        send("hello " + i++);
                    }
                }else{
                    send(msg);
                }
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
