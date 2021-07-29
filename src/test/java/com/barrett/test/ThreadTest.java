package com.barrett.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTest extends EntityBase {

    @Test
    public void run() {
        new Parent().run();
        log.info("主线程--------");
    }


    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            if (i % 10 == 0) {
                System.out.println(i + "-------------");
            }
        }
    }
}

class Parent extends EntityBase implements Runnable {

    DoOnceTask onceTask;
    int i = 0;

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            log.info("Parent >>>>>> start");
            sleep(2);
            if(read()){
                onceTask.setDoWork(true);
                new Thread(Child.getInstance(onceTask)).start();
            }else{
                onceTask.setDoWork(false);
            }
            log.info("Parent <<<<<< end");
            i++;
        }
    }

    public boolean read() {
        sleep(1);
        i++;
        if (i % 10 == 0) {
            return false;
        } else {
            return true;
        }
    }
}

class Child extends EntityBase implements Runnable {

    private static volatile Child instance;

    private DoOnceTask onceTask;

    //注意：同一个客户端只能提供一个对象
    public static Child getInstance(DoOnceTask onceTask) {
        if (instance == null) {
            synchronized (Child.class) {
                if (instance == null) {
                    instance = new Child(onceTask);
                }
            }
        }
        return instance;
    }

    private Child(DoOnceTask onceTask) {
        this.onceTask = onceTask;
    }

    @Override
    public void run() {
        log.info("child run   " + instance);
        sleep(5);
        if (onceTask.getDoWork()) {
            log.info("child 获取数据");
            onceTask.setDoWork(false);
        }
    }
}

class DoOnceTask {
    boolean doWork;

    public boolean getDoWork() {
        return doWork;
    }

    public void setDoWork(boolean doWork) {
        this.doWork = doWork;
    }
}

class EntityBase {
    static final Logger log = LoggerFactory.getLogger(EntityBase.class);

    public void sleep(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}