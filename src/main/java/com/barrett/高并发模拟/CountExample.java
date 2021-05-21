package com.barrett.高并发模拟;

import com.barrett.util.DateUtil;
import com.barrett.util.getId.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class CountExample {
    private static final Logger log = LoggerFactory.getLogger(CountExample.class);
    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    // 请求总数
    public static int clientTotal = 5;
    // 同时并发执行的线程数
    public static int threadTotal = 200;
    public static Sequence sequence = Sequence.getInst();

    //    public static int count = 0;//线程不安全
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量，此处用于控制并发的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        //闭锁，可实现计数器递减
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    //执行此方法用于获取执行许可，当总计未释放的许可数不超过200时，
                    //允许通行，否则线程阻塞等待，直到获取到许可。
                    semaphore.acquire();
                    //TODO 业务处理
                    add();

                    //释放许可
                    semaphore.release();
                } catch (Exception e) {
                    //log.error("exception", e);
                    e.printStackTrace();
                }
                //闭锁减一
                countDownLatch.countDown();
            });

//            log.info("计算: -> {}", count);
        }
        countDownLatch.await();//线程阻塞，直到闭锁值为0时，阻塞才释放，继续往下执行
        executorService.shutdown();

        //这里输出的值不一定是 clientTotal，正确的应该等于 clientTotal
//        log.info("count: -> {}", count);
    }

    private static void add() {
        //发起请求
        //TODO 业务处理
        long start = System.currentTimeMillis();
        long l = sequence.nextId();
//        String uid = IdUtil.getUid(1);
        long timestamp = DateUtil.timestamp(start, System.currentTimeMillis());
        System.out.println(sdf.format(new Date()) + "   " + timestamp + "ms --- " + l);
//        System.out.println(sdf.format(new Date())+"   "+timestamp+" --- "+uid);
//        count++;
        count.incrementAndGet();
    }
} 