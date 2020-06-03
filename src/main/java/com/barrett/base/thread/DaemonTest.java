package com.barrett.base.thread;

/**
 * 守护线程：是为用户线程服务的，jvm停止不用等待守护线程执行完毕
 * 默认：用户线程；jvm等待用户线程执行完毕才会停止
 *
 * @Author created by barrett in 2020/6/3 22:40
 */
public class DaemonTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new God());
        thread.setDaemon(true);//修改为守护线程
        thread.start();
        new Thread(new You()).start();
    }
}

class You implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <365*100 ; i++) {
            System.out.println("happy life");
        }
    }
}

class God implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <365*100000 ; i++) {
            System.out.println("god life");
        }
    }
}
