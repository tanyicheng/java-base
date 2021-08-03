package com.barrett.test;

import com.barrett.util.thread.Task;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static com.barrett.PLC.openTcp.ModbusMasterTCPDemo.*;

public class TaskTest {
    long period = 2000;
    static boolean flag = false;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(TaskTest.class.getName());


    public void taskChild(long period) {
        Task.INSTANCE.schedule("task1", new Task.ScheduleTask() {
            @Override
            public void run() {
                logger.info("  接受信号，at={}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                try {
                    //TODO 读取数据
//                    getPlcInfo();
//                    Number val = readHoldingRegister(1, 0, DataType.FOUR_BYTE_FLOAT);// 注意,float
//                    System.out.println("val："+val);

                    // 初始化modbus
//                    initModbusTcpMaster();
                    // 执行操作
                    // 读取模拟量
//                    System.out.println(readHoldingRegisters(0, 1, 1));

                    // 释放资源
                    release();
//                    Thread.sleep(3000);
//                    flag = true;
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public boolean stop() {
//                logger.info("stop------------  " + flag);
                return flag;
            }

        }, 0, period);
    }


    public void taskTest(String name,long period) {
        final int[] i = {0};
        Task.INSTANCE.schedule(name, new Task.ScheduleTask() {
            @Override
            public void run() {
                logger.info("start");
                try {
                    child(period);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                try {
////                    flag = true;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }finally {
//                }
                i[0]++;
            }

            @Override
            public boolean stop() {
//                logger.info("stop------------  " + flag);
                return flag;
            }

        }, 0, period);
    }

    public void child(long period){
        sleep(2);
        logger.info("子任务运行"+period);
        if(period==500){
            int a=1/0;
        }
    }
    @Test
    public void task() throws Exception {
        System.out.println("======主线程开始====== " + Thread.currentThread().getName());

        taskTest("tesk1",500);
//        taskTest("tesk2",3000);
//        taskTest("tesk3",5000);

//        Task.INSTANCE.schedule2("Scheduled1",0,1);
//        Task.INSTANCE.schedule2("Scheduled2",0,2);
//        Task.INSTANCE.schedule2("Scheduled3",0,3);

        System.out.println("======主线程结束====== " + Thread.currentThread().getName());

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    public  void sleep(int time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
