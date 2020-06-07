package com.barrett.base.thread;

/**
 * 生产者消费者
 *
 * @Author created by barrett in 2020/6/7 22:45
 */
public class ProductCustomer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producter p = new Producter(clerk);
        Customers c = new Customers(clerk);
        Thread tp1 = new Thread(p);//生产者1
        Thread tp2 = new Thread(p);//生产者2
        Thread tc1 = new Thread(c);//消费者1

        tp1.setName("生产1号");
        tp2.setName("生产2号");
        tc1.setName("消费1号");

        tp1.start();
        tp2.start();
        tc1.start();
    }

}

//店员
class Clerk{
    int productNum;//产品数量

    public synchronized void addProductNum(){
        if(productNum >= 20){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            productNum ++;
            System.out.println(Thread.currentThread().getName()+"生产第"+productNum+"个产品+++");
            notifyAll();//当产品为0时，只要生产一个就能消费，所以唤醒消费者的所有线程
        }
    }

    public synchronized void consumeProductNum(){
        if(productNum <= 0){
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            System.out.println(Thread.currentThread().getName()+"消费第"+productNum+"个产品---");
            productNum --;
            notifyAll();//当产品为20，消费一个才能继续生产，所以在此唤醒生产的所有线程
        }
    }
}
//生产者
class Producter implements Runnable{
    Clerk clerk;
    public Producter (Clerk clerk){
        this.clerk = clerk;
    }
    public void run(){
        while(true){
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            clerk.addProductNum();//生产产品
        }
    }
}
//消费者
class Customers implements Runnable{
    Clerk clerk;
    public Customers(Clerk clerk){
        this.clerk = clerk;
    }

    public void run(){
        while(true){
            try {
                Thread.currentThread().sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProductNum();//消费产品
        }
    }
}
