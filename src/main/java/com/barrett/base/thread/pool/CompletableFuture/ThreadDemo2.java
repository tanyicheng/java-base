package com.barrett.base.thread.pool.CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 等待所有任务执行完成
 * @author created by barrett in 2021/11/16 14:13
 **/
public class ThreadDemo2 {

    public static void main(String[] args) {
        ThreadDemo2 t = new ThreadDemo2();
        t.demo1();
    }

    //如果让你实现等待所有任务线程执行完成，再进行下一步操作
    //原来的方式 线程池+CountDownLatch，像下面这样：
    public void demo1() {
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Integer> list = Arrays.asList(1, 2, 3);
        CountDownLatch countDownLatch = new CountDownLatch(list.size());
        for (Integer key : list) {
            // 2. 提交任务
            executorService.execute(() -> {
                // 睡眠一秒，模仿处理过程
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                }
                System.out.println("结果" + key);
                countDownLatch.countDown();
            });
        }

        executorService.shutdown();
        // 3. 阻塞等待所有任务执行完成
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
        }
    }

    //使用CompletableFuture是怎么重构的

    /**
     * 1、遍历list集合，提交CompletableFuture任务，把结果转换成数组
     * 2、再把数组放到CompletableFuture的allOf()方法里面
     * 3、最后调用join()方法阻塞等待所有任务执行完成
     * CompletableFuture的allOf()方法的作用就是，等待所有任务处理完成
     *
     * @author created by barrett in 2021/11/16 14:11
     **/
    public void demo2() {
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Integer> list = Arrays.asList(1, 2, 3);
        // 2. 提交任务，并调用join()阻塞等待所有任务执行完成
        CompletableFuture
                .allOf(
                        list.stream().map(key ->
                                CompletableFuture.runAsync(() -> {
                                    // 睡眠一秒，模仿处理过程
                                    try {
                                        Thread.sleep(1000L);
                                    } catch (InterruptedException e) {
                                    }
                                    System.out.println("结果" + key);
                                }, executorService))
                                .toArray(CompletableFuture[]::new))
                .join();
        executorService.shutdown();
    }


}