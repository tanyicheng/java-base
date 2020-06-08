package com.barrett.base.thread;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 任务调度
 *
 * @Author created by barrett in 2020/6/8 21:19
 */
public class TimerTest01 {
    public static void main(String[] args) {
        Timer timer = new Timer();
//        timer.schedule(new MyTask(), 1000);//1秒后执行任务 myTask
//        timer.schedule(new MyTask(),1000,500);//1秒后执行，每500毫秒执行一次
        Calendar cal = new GregorianCalendar(2020,6,8,21,30,59);
        timer.schedule(new MyTask(),cal.getTime(),200);//指定具体日期
        
    }
}

class MyTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("放空大脑。。。");
    }
}