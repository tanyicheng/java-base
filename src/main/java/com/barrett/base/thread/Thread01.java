package com.barrett.base.thread;

/**
 * 多线程
 *
 * @Author created by barrett in 2020/5/29 20:33
 */
public class Thread01 {

    public static void main(String[] args) {
//        new MyThread().start();
//        new MyThead2().run();

        test(100000, 1);
    }

    public static void test(double money, int i) {
        if (i <= 7) {
            System.out.println("第"+i+"年"+money);
            money = money * 1.5;
            test(money,++i);
        } else {
            return;
        }
    }


    /**
     * 继承方式实现
     *
     * @Author created by barrett in 2020/5/29 22:37
     */
    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("thread 子线程" + Thread.currentThread().getName());
        }
    }

    static class MyThead2 implements Runnable {

        @Override
        public void run() {
            System.out.println("thread 子线程" + Thread.currentThread().getName());

        }
    }
}
