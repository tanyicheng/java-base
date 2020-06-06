package com.barrett.base.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 多线程下给容器添加数据时，不能锁方法，要锁具体对象 list
 * @Author created by barrett in 2020/6/2 22:09
 */
public class SyncThreadList {

    public static void main(String[] args) throws InterruptedException {
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (list){
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        Thread.sleep(2000);

        System.out.println(list.size());
    }


}
