package com.barrett.base.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TransferQueue;

/**
 * 银行取钱
 *
 * @Author created by barrett in 2020/6/2 22:09
 */
public class SyncThreadBank {

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account("彩票中奖金额", 100);
        Drawing you = new Drawing(account, 30, "你自己");
        Drawing wife = new Drawing(account, 20, "你妻子");

        you.start();
        wife.start();
    }


}

class Drawing extends Thread {

    Account account;//取钱账户
    int getMoney;//取钱数量
    int packetTotal;//身上的金额

    public Drawing(Account account, int getMoney, String name) {
        super(name);
        this.account = account;
        this.getMoney = getMoney;
    }

    @Override
    public void run() {
        test();
    }

    private void test() {
        while (true) {
            //TODO 为了提高性能
            if (account.money - getMoney <= 0) {
                return;
            }

            // TODO 这里就不能锁this对象了，要锁具体的共享资源的对象 account
            synchronized (account) {
                if (account.money - getMoney <= 0) {
                    System.out.println("余额不足");
                    return;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //账户扣除本次取钱金额
                account.money -= getMoney;
                packetTotal += getMoney;
                System.out.println("账户余额：" + account.money+"   口袋现金：" + packetTotal);
            }

        }
    }
}