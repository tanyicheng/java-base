package com.barrett.test;

import com.barrett.base.net.tcp.demo5.Base5;
import com.barrett.gof.编程式事物封装.TranTemplate;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseTest {
    public static Logger log = LoggerFactory.getLogger(BaseTest.class);

    public static void main(String[] args) {
        Long l = 1234567890123456789l;

//        System.out.println(l);
        exception();

    }

    //异常输出
    public static void exception() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
//            e.printStackTrace();
//            StackTraceElement[] stackTrace1 = e.getStackTrace();
//            for (StackTraceElement element : stackTrace1) {
//                System.out.println(element);
//            }

            String stackTrace = getStackTrace(e);
            System.out.println(stackTrace);
//            log.error("发生异常：",e);
        }
    }

    @Test
    public void testStr() {
        long l = System.currentTimeMillis();
        System.out.println(l);
        Date date = new Date();
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        System.out.println(dfs.format(date));
    }


    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }

    public static List<String> dzList(List<String> list){
        list.forEach((item)->{
            item +="aaa";
        });
        return null;
    }

    @Test
    public void test3(){
        List<String> list = new ArrayList<>();
        list.add("245");
        list.add("246");
        dzList(list);
    }
    @Test
    public void testTr(){
        TranTemplate tr = new TranTemplate();

        Object execute = tr.execute(status -> {
            System.out.println("业务逻辑:"+status);
            int a=1/0;
            return null;
        });
    }
}