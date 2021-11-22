package com.barrett.base.thread.pool.CompletableFuture;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 任何一个任务处理完成就返回
 * @author created by barrett in 2021/11/16 14:12
 **/
public class ThreadDemo3 {

    public static void main(String[] args) {
        ThreadDemo3 t = new ThreadDemo3();
        t.demo1();
    }

    /**
     * TODO 任何一个任务处理完成就返回
     * 如果要实现这样一个需求，往线程池提交一批任务，只要有其中一个任务处理完成就返回。 该怎么做？
     * 如果你手动实现这个逻辑的话，代码肯定复杂且低效，有了CompletableFuture就非常简单了，只需调用anyOf()方法就行了
     * @author created by barrett in 2021/11/16 14:14
     **/
    public void demo1(){
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Integer> list = Arrays.asList(1, 2, 3);
        long start = System.currentTimeMillis();
        // 2. 提交任务
        CompletableFuture<Object> completableFuture = CompletableFuture
                .anyOf(
                        list.stream().map(key ->
                                CompletableFuture.supplyAsync(() -> {
                                    // 睡眠一秒，模仿处理过程
                                    try {
                                        Thread.sleep(1000L);
                                    } catch (InterruptedException e) {
                                    }
                                    return "结果" + key;
                                }, executorService))
                                .toArray(CompletableFuture[]::new));
        executorService.shutdown();

        // 3. 获取结果
        try {
            System.out.println(completableFuture.get());
        } catch (Exception e) {
        }
    }

    /**
     * TODO 一个线程执行完成，交给另一个线程接着执行
     * @author created by barrett in 2021/11/16 14:14
     **/
    public void demo2(){
// 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 2. 提交任务，并调用join()阻塞等待任务执行完成
        String result2 = CompletableFuture.supplyAsync(() -> {
            // 睡眠一秒，模仿处理过程
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            return "结果1";
        }, executorService).thenApplyAsync(result1 -> {
            // 睡眠一秒，模仿处理过程
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            return result1 + "结果2";
        }, executorService).join();

        executorService.shutdown();
        // 3. 获取结果
        System.out.println(result2);
    }

    /**
     * then、handle方法使用示例
     * @author created by barrett in 2021/11/16 14:20
     **/
    public void demo3(){
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("1. 开始淘米");
            return "2. 淘米完成";
        }).thenApplyAsync(result -> {
            System.out.println(result);
            System.out.println("3. 开始煮饭");
            // 生成一个1~10的随机数
            if (RandomUtils.nextInt(1, 10) > 5) {
                throw new RuntimeException("4. 电饭煲坏了，煮不了");
            }
            return "4. 煮饭完成";
        }).handleAsync((result, exception) -> {
            if (exception != null) {
                System.out.println(exception.getMessage());
                return "5. 今天没饭吃";
            } else {
                System.out.println(result);
                return "5. 开始吃饭";
            }
        });

        try {
            String result = completableFuture.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //输出结果可能是：
        //1. 开始淘米
        //2. 淘米完成
        //3. 开始煮饭
        //4. 煮饭完成
        //5. 开始吃饭
        //也可能是：
        //1. 开始淘米
        //2. 淘米完成
        //3. 开始煮饭
        //java.lang.RuntimeException: 4. 电饭煲坏了，煮不了
        //5. 今天没饭吃
    }

    /**
     * complete方法使用示例
     * @author created by barrett in 2021/11/16 14:21
     **/
    public void demo4(){
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "饭做好了";
        });

        //try {
        //    Thread.sleep(1L);
        //} catch (InterruptedException e) {
        //}

        completableFuture.complete("饭还没做好，我点外卖了");
        System.out.println(completableFuture.join());
        //输出结果：
        //饭还没做好，我点外卖了
        //如果把注释的sleep()方法放开，输出结果就是:
        //饭做好了
    }

    /**
     * either方法使用示例
     * @author created by barrett in 2021/11/16 14:22
     **/
    public void demo5(){
        CompletableFuture<String> meal = CompletableFuture.supplyAsync(() -> {
            return "饭做好了";
        });
        CompletableFuture<String> outMeal = CompletableFuture.supplyAsync(() -> {
            return "外卖到了";
        });

        // 饭先做好，就吃饭。外卖先到，就吃外卖。就是这么任性。
        CompletableFuture<String> completableFuture = meal.applyToEither(outMeal, myMeal -> {
            return myMeal;
        });

        System.out.println(completableFuture.join());

        //输出结果可能是：
        //饭做好了
        //也可能是：
        //外卖到了
    }


}