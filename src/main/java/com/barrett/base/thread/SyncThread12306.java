package com.barrett.base.thread;

/**
 * 抢票
 * TODO 考虑在网络延迟、高并发等情况下会出现负数的情况
 * {}普通块，局部块，构造块，静态块，同步块
 * synchronized 锁资源注意：一定要锁具体的共享资源
 *
 * @Author created by barrett in 2020/6/2 22:09
 */
public class SyncThread12306 implements Runnable {

    private int tickets = 10;
    private boolean flag = true;

    @Override
    public void run() {
//        synchronized (this) { //可以解决负数问题，但是效率太低
        while (flag) {
            try {
                test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        }
    }

    public synchronized void test() throws InterruptedException {
        if (tickets <= 0) {
            flag = false;
            System.out.println(Thread.currentThread().getName() +" sorry 售票结束");
            return;
        }
        //模拟网络延迟
        Thread.sleep(200);
        System.out.println(Thread.currentThread().getName() + " 取第 " + tickets-- +" 张票");
    }

    public static void main(String[] args) {
        //一份资源
        SyncThread12306 t1 = new SyncThread12306();
        //多个代理
        new Thread(t1, "张三").start();
        new Thread(t1, "李四").start();
        new Thread(t1, "王五").start();
        new Thread(t1, "贾如").start();
        new Thread(t1, "天赐").start();
    }
}
