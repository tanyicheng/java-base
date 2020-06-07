package com.barrett.base.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 另一种方式的购票
 *
 * @Author created by barrett in 2020/6/6 22:06
 */
public class Sync12306 {
    public static void main(String[] args) {
        Web12306 sy = new Web12306(5,"火车票");
        new Passenger(sy, "无极", 2).start();
        new Passenger(sy, "李迪", 4).start();
    }
}

//顾客
class Passenger extends Thread {
    int seats;

    public Passenger(Runnable target, String name, int seats) {
        super(target, name);
        this.seats = seats;
    }
}

class Web12306 implements Runnable {
    int vaildSeat;//可用位置
    String name;

    public Web12306(int vaildSeat, String name) {
        this.vaildSeat = vaildSeat;
        this.name = name;
    }

    @Override
    public void run() {
        Passenger p = (Passenger) Thread.currentThread();
        boolean b = this.bookTickets(p.seats);
        if (b) {
            System.out.println(Thread.currentThread().getName() + " 购票成功；数量：" + p.seats);
        } else {
            System.out.println(Thread.currentThread().getName() + " 购票失败；数量不足 "+p.seats);
        }
    }

    //购票 使用同步方法
    public synchronized boolean bookTickets(int seats) {
        System.out.println("剩余票数：" + vaildSeat);
        if(seats>vaildSeat){
            return false;
        }
        vaildSeat -= seats;
        return true;

    }

}