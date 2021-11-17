package com.barrett.base.thread.pool.CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池使用示例
 * 参考 https://www.toutiao.com/a7029664569698271783/?log_from=d55c7841bf35f_1636935725245
 * @author created by barrett in 2021/11/16 08:14
 **/
public class ThreadDemo {

    /**
     * 缺点:
     * 1、获取结果时，调用的future.get()方法，会阻塞当前线程，直到返回结果，大大降低性能
     * 2、输出结果不确定，321,123,132 都有
     *
     * @author created by barrett in 2021/11/16 11:25
     **/
    public static void main(String[] args) {
        ThreadDemo t = new ThreadDemo();
//        t.demo1();
        t.demo2();
    }

    public void demo1() {
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Integer> list = Arrays.asList(1, 2, 3);
        for (Integer key : list) {
            // 2. 提交任务
            CompletableFuture.supplyAsync(() -> {
                // 睡眠一秒，模仿处理过程
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                }
                return "结果" + key;
            }, executorService).whenCompleteAsync((result, exception) -> {
                // 3. 获取结果
                System.out.println(result);
            });
            ;
        }

        executorService.shutdown();
        // 由于whenCompleteAsync获取结果的方法是异步的，所以要阻塞当前线程才能输出结果
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 代码中使用了CompletableFuture的两个方法，
     * supplyAsync()方法作用是提交异步任务，有两个传参，任务和自定义线程池。
     * whenCompleteAsync()方法作用是异步获取结果，也有两个传参，结果和异常信息。
     * <p>
     * 代码经过CompletableFuture改造后，是多么的简洁优雅。 提交任务也不用再关心线程池是怎么使用了，获取结果也不用再阻塞当前线程了。
     * 如果你比较倔强，还想同步获取结果，可以使用whenComplete()方法，或者单独调用join()方法。 join()方法配合Stream流是这样用的：
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
