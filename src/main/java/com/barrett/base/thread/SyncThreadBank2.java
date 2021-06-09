package com.barrett.base.thread;


/**
 * 银行存取钱并发测试
 *
 * @author created by barrett in 2021/6/8 14:04
 **/
public class SyncThreadBank2 {

    public static void main(String args[]) {

        Acount acount = new Acount(0);
//        Bank b = new Bank(acount);
//        ConsumerA c = new ConsumerA(acount);
//        new Thread(b).start();
//        new Thread(c).start();
        ThreadPoolUtils.executor.execute(new ConsumerA(acount));
        ThreadPoolUtils.executor.execute(new Bank(acount));
    }
}

/**
 * 银行账户
 *
 * @author created by barrett in 2021/6/8 13:52
 **/
class Acount implements Runnable {

    private int money;

    public Acount(int money) {
        this.money = money;
    }

    @Override
    public void run() {

    }

    public synchronized void getMoney(int money) {
        // 注意这个地方必须用while循环，因为即便再存入钱也有可能比取的要少
        while (this.money < money) {
            System.out.println("准备取款：" + money + " 余额：" + this.money + " 余额不足，正在等待存款......");
            try {
                wait();
            } catch (Exception e) {
            }
        }
        this.money = this.money - money;
        System.out.println("取出：" + money + " 还剩余：" + this.money);

    }

    public synchronized void setMoney(int money) {

        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
        this.money = this.money + money;
        System.out.println("新存入：" + money + " 共计：" + this.money);
        notify();
    }

}

// 存款类
class Bank implements Runnable {
    Acount Acount;

    public Bank(Acount Acount) {
        this.Acount = Acount;
    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int temp = 200;
//            int temp = (int) (Math.random() * 1000);
            Acount.setMoney(temp);
        }
    }

}

// 取款类
class ConsumerA implements Runnable {
    Acount Acount;

    public ConsumerA(Acount Acount) {
        this.Acount = Acount;
    }

    @Override
    public void run() {
        while (true) {
            int temp = 1000;
//            int temp = (int) (Math.random() * 1000);
            Acount.getMoney(temp);
        }
    }
}
