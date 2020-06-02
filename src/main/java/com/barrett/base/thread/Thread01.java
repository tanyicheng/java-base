package com.barrett.base.thread;

import org.apache.commons.io.FileUtils;

/**
 * 多线程
 * @Author created by barrett in 2020/5/29 20:33
 */
public class Thread01 {

    public static void main(String[] args) {
        new MyThread().start();
    }

    /**
     * 继承方式实现
     * @Author created by barrett in 2020/5/29 22:37
     */
    static class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println("thread 子线程");
        }
    }

    static class MyThead2 implements Runnable{

        @Override
        public void run() {

        }
    }
}
