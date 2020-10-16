package com.barrett.util.thread;


/**
 * //TODO 测试 synchronized
 * @Author barrett
 * @Date 2020/10/16 14:02
 **/
public class SyncTest implements Runnable {

    private int i = 0;


    @Override
    public void run() {
        business();
    }

    public static void main(String[] args) {
        //一份资源
        SyncTest t1 = new SyncTest();
        //多个代理
        new Thread(t1, "A").start();
        new Thread(t1, "B").start();

    }


    public synchronized void business() {
        System.out.println(Thread.currentThread().getName() + " > 进入方法：" + i);
        if (i == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " > 业务逻辑处理中...");
                Thread.sleep(1000l);
                i++;
                System.out.println(Thread.currentThread().getName() + " > " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " > 执行结束");
        } else {
            System.out.println(Thread.currentThread().getName() + " > 已经处理过，不再处理！");
        }
    }

}
