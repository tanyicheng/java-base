package com.barrett.base.net.udp;

public class TalkStudent {
    public final static int port = 9999;

    public static void main(String[] args) {
        //发送
        new Thread(new TalkSend(port, TalkTeacher.ip, TalkTeacher.port)).start();

        new Thread(new TalkReceive(7777)).start();

    }
}
