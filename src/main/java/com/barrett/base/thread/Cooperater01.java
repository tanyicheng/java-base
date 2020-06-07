package com.barrett.base.thread;

import com.sun.xml.internal.ws.streaming.TidyXMLStreamReader;

/**
 * 线程通信：管程法；生产者消费者、缓冲区
 *
 * @Author created by barrett in 2020/6/7 14:49
 */
public class Cooperater01 {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();
        new Productor(synContainer,"生产1号").start();
        new Productor(synContainer,"生产2号").start();
        new Consumer(synContainer,"消费者").start();
    }

}

//生产者
class Productor extends Thread {
    SynContainer synContainer;

    public Productor(SynContainer synContainer,String name) {
        super(name);
        this.synContainer = synContainer;
    }

    //生产
    public void run() {
        for (int i = 1; i <= 100; i++) {
            Bun bun = new Bun(i);
            synContainer.push(bun);
            System.out.println("+++生产面包" + i);
        }
    }
}

//消费者
class Consumer extends Thread {
    SynContainer synContainer;

    public Consumer(SynContainer synContainer,String name) {
        super(name);
        this.synContainer = synContainer;
    }

    //消费
    public void run() {
        for (int i = 0; i < 100; i++) {
            int id = synContainer.pop().id;
            System.out.println("---消费面包" + id);
        }
    }
}

//缓冲区
class SynContainer {
    Bun[] buns = new Bun[10];//存储容器
    int idx = 0;//计数器

    //生产
    public synchronized void push(Bun bun) {
        if (idx == buns.length-1) {
            //缓冲区满了，停止生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buns[idx] = bun;
        idx++;
        this.notifyAll();//通知消费者消费
    }

    //消费
    public synchronized Bun pop() {
        //缓冲区没有数据时等待
        if (idx == 0) {
            try {
                this.wait();//线程阻塞，等待生产者通知解除
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        idx--;
        Bun bun = buns[idx];
        this.notifyAll();//通知生产者生产
        return bun;
    }

}

//馒头
class Bun {
    int id;

    public Bun(int id) {
        this.id = id;
    }
}