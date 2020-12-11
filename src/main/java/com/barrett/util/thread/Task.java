package com.barrett.util.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程工具类
 * @author fishcat
 * @date 2018/11/13 10:48 AM
 */
public enum Task {

    INSTANCE;

    Task() {}

    /**
     * 定时任务
     * @param name      任务名
     * @param task      任务
     * @param delay     延时启动时间(单位：毫秒）
     * @param period    周期时间(单位：毫秒）
     */
    public void schedule(String name, ScheduleTask task, long delay, long period) {
        Timer timer = new Timer(name, true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (task.stop()) timer.cancel();
                else task.run();
            }
        }, delay, period);
    }

    public abstract static class ScheduleTask {
        public abstract void run();
        public abstract boolean stop();
    }

    /**
     * 仅仅执行一次的任务
     * @param name      任务名
     * @param task      任务
     * @param delay     延时启动时间(单位：毫秒）
     */
    public void once(String name, OnceTask task, long delay)  {
        Timer timer = new Timer(name, true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, delay);  // period没有设置默认为0，仅仅执行一次，timer就停止
    }

    public abstract static class OnceTask {
        public abstract void run();
    }


    /**
     * 守护线程
     * @param name  任务名
     * @param task  任务
     */
    public void daemon(String name, Runnable task) {
        Thread thread = new Thread(task, name);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * 独立线程，用于快速任务
     * @param name 任务名
     * @param task 任务
     */
    public void standalone(String name, Runnable task) {
        new Thread(task, name).start();
    }

    /**
     * 超时任务
     * @param name      任务名
     * @param expire    超时时间(单位：秒)
     * @param task      任务
     */
    public void timeout(String name, long expire, TimeoutTask task) {
        Thread taskThread = new Thread(task, name);
        Thread timeoutMonitorThread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(expire);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (taskThread.isAlive()) taskThread.interrupt();   // 标记线程中断，注意 task 需要对 Thread.currentThread().interrupted() 做判断，优雅超时处理
        }, String.format("%s-TimeoutMonitor", name));

        timeoutMonitorThread.start();
        taskThread.start();
    }

    /**
     * 构造线程池
     * 核心线程数为0，不会阻塞线程池退出，keepalive为2s
     */
//    private ThreadPoolExecutor inOrderExecutorService = new ThreadPoolExecutor(
//            0, Integer.MAX_VALUE, 2, TimeUnit.SECONDS,
//            new SynchronousQueue<>(), r -> {
//                Thread thread = new Thread(r);
//                thread.setName("OrderExecutorService");
//                return thread;
//            });

    private ExecutorService inOrderExecutorService = Executors.newSingleThreadExecutor();

    /**
     * 按序逐个执行任务
     * @param task  任务对象
     */
    public void runInOrder(Runnable task) {
        inOrderExecutorService.submit(task);
    }

    public void shutdownExecutorService() {
        inOrderExecutorService.shutdown();
    }
}
