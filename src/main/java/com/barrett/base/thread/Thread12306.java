package com.barrett.base.thread;

/**
 * 抢票
 * TODO 考虑在网络延迟、高并发等情况下会出现负数的情况
 *
 * @Author created by barrett in 2020/6/2 22:09
 */
public class Thread12306 implements Runnable {

    private int tickets = 99;

    @Override
    public void run() {
//        synchronized (this) { //可以解决负数问题，但是效率太低
            while (tickets > 0) {
                try {
                    //模拟网络延迟
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 取票 " + tickets--);
            }
//        }
    }

    public static void main(String[] args) {
        //一份资源
        Thread12306 t1 = new Thread12306();
        //多个代理
        new Thread(t1, "张三").start();
        new Thread(t1, "李四").start();
        new Thread(t1, "王五").start();
        new Thread(t1, "贾如").start();
        new Thread(t1, "天赐").start();
    }
}
