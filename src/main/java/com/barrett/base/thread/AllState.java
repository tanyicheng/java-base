package com.barrett.base.thread;

import javax.swing.plaf.TableHeaderUI;

/**
 * 线程的状态
 *
 * @Author created by barrett in 2020/6/3 22:03
 */
public class AllState {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " -> 线程运行");
            try {
                System.out.println("阻塞中。。。");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        printState(thread);//NEW
        thread.start();

        printState(thread);//RUNNABLE

        //监听线程状态
        while(!thread.getState().equals(Thread.State.TERMINATED)){
            int i = Thread.activeCount();//活动的线程数,也可以用线程数判断循环停止

            try {
                Thread.sleep(1000);
                printState(thread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printState(thread);
    }

    //观察线程状态
    public static void printState(Thread thread){
        System.out.println(thread.getState());

    }
}
