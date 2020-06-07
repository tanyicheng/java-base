package com.barrett.base.thread;

/**
 * 协作：生产者消费者：信号灯
 * 借助标志位
 * @Author created by barrett in 2020/6/7 14:49
 */
public class Cooperater02 {

    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

//生产者 演员
class Player extends Thread{
    TV tv = new TV();

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            this.tv.play("奇葩说");
        }
    }
}
//消费者 观众
class Watcher extends Thread{
    TV tv = new TV();

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i <20 ; i++) {
            tv.watch();

        }
    }
}
//同一个资源 电视
class TV{
    String voice;
    //信号灯
    // T 表示演员表演 观众等待
    // F 表示观众观看 演员等待
    boolean flag = true;

    //表演
    public synchronized void play(String voice){
        //演员等待
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.voice=voice;
        System.out.println("开始表演："+voice);
        //唤醒
        this.notifyAll();
        //切换标识
        flag=!flag;
    }
    //观看
    public synchronized void watch(){
        //观众等待
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("收看："+voice);
        //唤醒
        this.notifyAll();
        //切换标识
        flag=!flag;
    }
}
