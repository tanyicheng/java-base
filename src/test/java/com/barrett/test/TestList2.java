package com.barrett.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * fixme-1 待研究，为什么会抛异常
 * Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: -1
 * @author created by barrett in 2021/4/6 09:08
 **/
public class TestList2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LoveHuanHuanSis loveHuanHuanSis = new LoveHuanHuanSis();
        new Thread(() -> {
            while (true) {
                loveHuanHuanSis.pop();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                loveHuanHuanSis.push("");
            }
        }).start();

        new Thread(() -> {
            while (true) {
                loveHuanHuanSis.pop();
            }
        }).start();
    }

    static class LoveHuanHuanSis {
        List<String> list = new ArrayList<>();
        synchronized void push(String v) {
            list.add(v);
//            System.out.println("push------------   "+list.size());
            notify();
//            notifyAll();
        }

        synchronized String pop() {
            if (list.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            System.out.println("pop------------   "+list.size());
            return list.remove(list.size() - 1);
        }
    }

}