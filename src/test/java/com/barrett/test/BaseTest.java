package com.barrett.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    public static void main(String[] args) {
        Long l = 1234567890123456789l;

        System.out.println(l);
    }

    @Test
    public void testStr(){
        long l = System.currentTimeMillis();
        System.out.println(l);
        Date date = new Date();
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        System.out.println(dfs.format(date));
    }
}
