package com.barrett.base.thread;

public class DeadLock {
    String story = "故事";
    String wine = "酒";

    public  void wantWine() throws InterruptedException {
        synchronized (story){
            System.out.println("已经拥有："+ story +"就缺："+ wine);
            Thread.sleep(1000);
            synchronized (wine){
                System.out.println("拥有："+ wine);
            }
        }
    }

    public void wantStory() throws InterruptedException {
        synchronized (wine){
            System.out.println("已经拥有："+ wine +"就缺："+ story);
            Thread.sleep(1000);
            synchronized (story){
                System.out.println("拥有："+ story);
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deadLock.wantWine();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deadLock.wantStory();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}

