package com.barrett.base.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 电影院买票（座次）
 *
 * @Author created by barrett in 2020/6/2 22:09
 */
public class SyncThreadCinema {

    public static void main(String[] args) throws InterruptedException {
        //影院的位置
        List<Integer> all = new ArrayList<>();
        all.add(1);
        all.add(2);
        all.add(3);
        all.add(4);
        all.add(6);
        all.add(7);
        //客户选择的位置
        List<Integer> seats1 = new ArrayList<>();
        seats1.add(1);
        seats1.add(2);
        List<Integer> seats2 = new ArrayList<>();
        seats2.add(1);
        seats2.add(4);


        Cinema cinema = new Cinema(all, "开心影院");
        new Thread(new Customer(cinema, seats1), "张三").start();
        new Thread(new Customer(cinema, seats2), "李四").start();
    }


}

//顾客
class Customer implements Runnable {
    Cinema cinema;
    List<Integer> seats;//购买电影票数量

    public Customer(Cinema cinema, List<Integer> seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        boolean b = cinema.bookTickets(seats);
        if (b) {
            System.out.println(Thread.currentThread().getName() + " 购票成功；位置：" + seats);
        } else {
            System.out.println(Thread.currentThread().getName() + " 购票失败；没有可用座位 "+seats);
        }
    }
}

//影院
class Cinema {

    List<Integer> vaildSeat;//电影票数量
    String name;//名称

    public Cinema(List<Integer> vaildSeat, String name) {
        this.vaildSeat = vaildSeat;
        this.name = name;
    }

    //购票
    public boolean bookTickets(List<Integer> seats) {
        //TODO 加锁
        synchronized (vaildSeat) {
            System.out.println("剩余票数：" + vaildSeat);
            List<Integer> copy = new ArrayList<>();
            copy.addAll(vaildSeat);

            //取差集
            copy.removeAll(seats);
            //判断大小
            if(vaildSeat.size()-copy.size() != seats.size()){
                return  false;
            }
            //购票成功
            vaildSeat = copy;
            return true;
        }

    }


}