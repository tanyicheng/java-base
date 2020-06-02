package com.barrett.base.thread;

import javax.swing.plaf.TableHeaderUI;

/**
 * 龟兔赛跑
 * @Author created by barrett in 2020/6/2 22:23
 */
public class Racer implements Runnable{
    private static String winner;//胜利者
    @Override
    public void run() {
        //模拟步数=100步
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName()+"-->"+ i);
            boolean flag = gameOver(i);
            if(flag){
                break;
            }
        }
    }

    private boolean gameOver(int steps) {
        if(winner != null){
            return true;
        }else{
            if(steps == 100){
                winner=Thread.currentThread().getName();
                System.out.println("胜利者："+winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Racer racer = new Racer();

        new Thread(racer,"兔子").start();
        new Thread(racer,"乌龟").start();
    }
}
