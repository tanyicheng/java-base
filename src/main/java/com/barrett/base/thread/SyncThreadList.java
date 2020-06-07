package com.barrett.base.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 多线程下给容器添加数据时，不能锁方法，要锁具体对象 list
 *
 * @Author created by barrett in 2020/6/2 22:09
 */
public class SyncThreadList {

    public static void main(String[] args) throws InterruptedException {
        new SyncThreadList().test();
    }

    /**
     * 使用juc 实现并发容器
     *
     * @Author created by barrett in 2020/6/6 22:24
     */
    public void test2() throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(2000);

        System.out.println(list.size());
    }

    //手动实现并发容器
    public void test() throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        Thread.sleep(2000);

        System.out.println(list.size());
    }

}
