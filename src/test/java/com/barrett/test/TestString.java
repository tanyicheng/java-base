package com.barrett.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字符拼接效率测试
 * 1、执行了：22208ms！TODO  因为每次都要新建一个对象，所以效率最慢
 * 2、执行了：22ms！
 * 3、执行了：20ms！
 * @author created by barrett in 2020/12/13 10:34
 **/
public class TestString {

    private static long start = 0l;
    private static long end = 0l;
    private static String result = "";
    private static int len = 100000;

    @Before
    public void before() {
        start = System.currentTimeMillis();
    }

    @After
    public void after() {
        end = System.currentTimeMillis();
        int time = (int) ((end - start));
        System.out.println("执行了：" + time + "ms！");
        System.out.println(result);
    }


    @Test
    public void test1() {
        String a = "a";
        for (int i = 0; i < len; i++) {
            a += i;
        }
        result = a;
    }

    @Test
    public void test2() {
        List<String> a = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            a.add("a");
        }
        result = StringUtils.join(a, ",");
    }

    @Test
    public void test3() {
        String[] a = new String[len];
        for (int i = 0; i < len; i++) {
            a[i] = "a";
        }
        result = StringUtils.join(Arrays.asList(a), ",");
    }
}
