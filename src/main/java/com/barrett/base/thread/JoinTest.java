package com.barrett.base.thread;

/**
 * 父亲叫儿子买烟
 *
 * @Author created by barrett in 2020/6/3 21:20
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {

        Father father = new Father();
        father.run();
    }
}

class Father implements Runnable{
    @Override
    public void run() {
        System.out.println("1--父亲想抽烟，发现没烟了");
        Thread t = new Thread(new Sun());
        t.start();
        try {
            t.join();//father 阻塞，等待儿子执行完毕，也可以加入等待时间，超时则不等，继续执行
            System.out.println("4--儿子买烟回来，把钱交给父亲");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Sun implements Runnable{
    @Override
    public void run() {
        System.out.println("2--儿子接过父亲的钱，准备去买烟");
        for (int i = 0; i <10 ; i++) {
            System.out.println("3--玩游戏  "+ i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
