package com.barrett.test;

import com.barrett.beans.Person;
import org.junit.Test;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

import static com.barrett.list.ListExample.sameList;

public class TestList {

    public static void main(String[] args) {
        test2();
    }


    public static void test1() {
        List<Person> list = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Person p = new Person(i,"张三","99","中国","123456890");
            list.add(p);
        }
        Person p2 = new Person(5,"张三","99","中国","123456890");

        List<Person> remove = new ArrayList<>();
//        remove.add(p2);
        list.remove(p2);
        Person rem = null;

        for (Person person : list) {
            if(person.getId() ==5){
                remove.add(person);
                rem = person;
            }
            System.out.println(person);
        }
//        list.removeAll(remove);
        list.remove(rem);
        System.out.println(list.size());
    }

    public static void test2() {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add("str-"+i);
        }
//        list.remove("str-5");
        list.add("航三");
        list.add("航四");
        list.add("航一");
        list.add("航二");
        Collections.reverse(list);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(list.size());
    }

    public static class AtomicExample {
        private static int[] value = new int[]{1, 2, 3};
        private static AtomicIntegerArray integerArray = new AtomicIntegerArray(value);

        public static void main(String[] args) {
            //对数组中索引为2的位置的元素加3
            int result = integerArray.getAndAdd(2, 3);
            System.out.println(integerArray.get(2));
            System.out.println(result);
        }
    }// 6 3
}
