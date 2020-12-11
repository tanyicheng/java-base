package com.barrett.test;

import com.barrett.util.thread.Task;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static com.barrett.PLC.openTcp.ModbusMasterTCPDemo.*;

public class TaskTest {
    long period = 500;
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

                    // 初始化资源
                    initModbusTcpMaster();
                    // 执行操作
                    // 读取模拟量
                    System.out.println(readHoldingRegisters(0, 1, 1));

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

    @Test
    public void task() throws Exception {
        System.out.println("======主线程开始====== " + Thread.currentThread().getName());

        taskChild(period);

        System.out.println("======主线程结束====== " + Thread.currentThread().getName());

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }


}
