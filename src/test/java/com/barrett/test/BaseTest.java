package com.barrett.test;

import com.barrett.base.net.tcp.demo5.Base5;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
//            String stackTrace = getStackTrace(e);
//            System.out.println(stackTrace);
            log.error("发生异常：",e);
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
}
