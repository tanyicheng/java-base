package com.barrett.base.thread;

/**
 * 死锁：过多的同步可能造成相互不释放资源
 * 从而相互等待，一般发生于同步中持有多个对象的锁
 * 避免：不要在同一个代码块中，同时持有多个对象锁
 * @Author created by barrett in 2020/6/7 10:42
 */
public class DeadLock2 {
    public static void main(String[] args) {
        new MarkUp(0,"丫鬟").start();
        new MarkUp(1,"皇后").start();
    }
}

//口红
class Lipstick {

}

//镜子
class Mirror {

}

//化妆
class MarkUp extends Thread {
    //使用静态变量，对象只有一份
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;//选择
    String name;

    public MarkUp(int choice, String name) {
        this.choice = choice;
        this.name = name;
    }

    @Override
    public void run() {
        markup();
    }

    //相互持有对方的对象锁
    private void markup() {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(name + "拿到口红");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //TODO 造成死锁
               /* synchronized (mirror) {
                    System.out.println(name + "拿到镜子");
                }*/
            }
            //TODO 避免死锁
            synchronized (mirror) {
                System.out.println(name + "拿到镜子");
            }
        }else{
            synchronized (mirror) {
                System.out.println(name + "拿到镜子");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //TODO 造成死锁
//                synchronized (lipstick) {
//                    System.out.println(name + "拿到口红");
//                }
            }
            //TODO 避免死锁
            synchronized (lipstick) {
                System.out.println(name + "拿到口红");
            }
        }
    }
}
