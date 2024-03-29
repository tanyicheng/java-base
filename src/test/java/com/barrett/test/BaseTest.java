package com.barrett.test;

import com.alibaba.fastjson.JSON;
import com.barrett.base.json.JsonUtils;
import com.barrett.base.net.tcp.demo5.Base5;
import com.barrett.beans.Person;
import com.barrett.gof.编程式事物封装.TranTemplate;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseTest {
    public static Logger log = LoggerFactory.getLogger(BaseTest.class);

    public static void main(String[] args) {
        Long l = 1234567890123456789l;

//        System.out.println(l);
//        exception();

//        Double a = 2.3;
//        Double b = 6.3;
//        System.out.println(new BigDecimal(100 * a));
//        test1(3, 34);

        Person p = new Person(1, "张三", "18");
        Person b = new Person();
        BeanUtils.copyProperties(p, b);
        b.setAge("25");

        System.out.println(p.toString());
        System.out.println(b.toString());

    }

    public static void test1(double size, int passRate) {
        for (int i = 1; i <= size; i++) {
            System.out.println(i / size * 100);
            if (i / size * 100 >= passRate) {
                System.out.println("true,会签通过至少人数" + i);
                break;
            }
        }
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

    public static List<String> dzList(List<String> list) {
        list.forEach((item) -> {
            item += "aaa";
        });
        return null;
    }

    @Test
    public void test3() {
        List<String> list = new ArrayList<>();
        list.add("245");
        list.add("246");
        dzList(list);
    }

    @Test
    public void testTr() {
        TranTemplate tr = new TranTemplate();

        Object execute = tr.execute(status -> {
            System.out.println("业务逻辑:" + status);
            int a = 1 / 0;
            return null;
        });
    }

    @Test
    public void test4() {
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("abcdefg" + i);
            list2.add("abcdefg" + i);
        }
        String ids = String.join(",", list);
        System.out.println("--->" + ids);

        int i = 0;
        for (String id : list2) {
            ++i;
            if (StringUtils.isNotEmpty(ids) && ids.contains(id)) {
                ids = ids.replace(id + ",", "");
                System.out.println(i + "--->" + ids);
                continue;
            }
            System.out.println(i + "=======");
        }
    }

    @Test
    public void test5() {
        List<Integer> all = new ArrayList<>();
        all.add(1);
        all.add(2);
        all.add(3);
        all.add(4);
        all.add(5);
        List<Integer> old = new ArrayList<>();
        old.add(1);
        old.add(3);
        old.add(5);
        List<Integer> install = new ArrayList<>();
        List<Integer> update = new ArrayList<>();
        for (Integer id : all) {
            boolean flag = true;
            //更新
            for (Integer id2 : old) {
                if (id == id2) {
                    flag = false;
                    update.add(id);
                    break;
                }
            }
            //新增
            if (flag)
                install.add(id);
        }
        System.out.println(JSON.toJSONString(update));
        System.out.println(JSON.toJSONString(install));
    }

    @Test
    public void test6() {
        Map map = new HashMap();
        map.put("a",6);
        map.put("b",6);
        Integer a= (Integer) map.get("a");
        Integer b= (Integer) map.get("b");
        System.out.println(a.equals(b));
    }
}