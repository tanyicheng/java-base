package com.barrett.base.net.udp;

public class TalkTeacher {
    public final static String ip = "localhost";
    public final static int port = 8888;

    public static void main(String[] args) {
        new Thread(new TalkReceive(port)).start();

        new Thread(new TalkSend(6666,ip,7777)).start();

    }
}
