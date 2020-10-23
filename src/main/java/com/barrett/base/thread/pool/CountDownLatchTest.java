package com.barrett.base.thread.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * //TODO 主线程等待子线程执行完毕后执行
 *
 * @Author barrett
 * @Date 2020/10/22 16:57
 **/
public class CountDownLatchTest {

    private static int flag = 0;

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);
        System.out.println("主线程开始执行…… ……" + flag);

        //第一个子线程执行
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        es1.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    flag = 1;
                    System.out.println("子线程：" + Thread.currentThread().getName() + "执行 " + flag);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        es1.shutdown();

        //第二个子线程执行
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    flag = 2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程：" + Thread.currentThread().getName() + "执行 " + flag);
                latch.countDown();
            }
        });
        es2.shutdown();

        System.out.println("等待两个线程执行完毕…… ……" + flag);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程 " + flag);
    }
}