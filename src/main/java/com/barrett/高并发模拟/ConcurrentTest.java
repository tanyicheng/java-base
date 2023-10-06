package com.barrett.高并发模拟;


import com.barrett.util.DateUtil;
import com.barrett.util.getId.Sequence;
import org.apache.http.HttpException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;


/**
 * 模拟用户的并发请求，检测用户乐观锁的性能问题
 *
 * @author fxb
 * @date 2018/3/29 18:55
 */
public class ConcurrentTest {

    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String[] args) {
        //模拟10000人并发请求，用户钱包
        CountDownLatch latch = new CountDownLatch(1);
        //模拟10000个用户
        for (int i = 0; i < 6; i++) {
            AnalogUser analogUser = new AnalogUser("user" + i, 123L, "1", "20.024", latch);
            analogUser.start();
        }
        //计数器減一  所有线程释放 并发访问。
        latch.countDown();
//        System.out.println("所有模拟请求结束  at " + sdf.format(new Date()));

    }

    static class AnalogUser extends Thread {
        //模拟用户姓名
        String workerName;
        //        String openId;
        Long openId;
        String openType;
        String amount;
        CountDownLatch latch;

        public AnalogUser(String workerName, Long openId, String openType, String amount,
                          CountDownLatch latch) {
            super();
            this.workerName = workerName;
            this.openId = openId;
            this.openType = openType;
            this.amount = amount;
            this.latch = latch;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                latch.await(); //一直阻塞当前线程，直到计时器的值为0
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            post();//发送post 请求


        }

        //并发任务
        public void post() {
            String result = "";
            long timestamp = 0L;
            long l = 0L;
            System.out.println("模拟用户： " + workerName + " --- " + openId + " 开始");
            try {

                //TODO 业务处理
                long l1 = System.currentTimeMillis();
                l = Sequence.getInst().nextId();
                timestamp = DateUtil.timestamp(l1, System.currentTimeMillis());

//            result = HttpUtil.sendGet("http://localhost:8080/api/collect/distribution/redis/lock1",null);
            } catch (Exception e) {

            }
            //sendPost("http://localhost:8080/Settlement/wallet/walleroptimisticlock.action", "openId="+openId+"&openType="+openType+"&amount="+amount);
//            System.out.println("操作结果："+result);
            System.out.println(sdf.format(new Date()) + " 模拟用户： "+l +"  "+ workerName +"  "+ timestamp + "ms");

        }
    }

}