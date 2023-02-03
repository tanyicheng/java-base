package com.barrett.list;

import com.barrett.beans.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 常用数组案例
 *
 * @Author created by barrett in 2023/2/3 14:32
 */
public class ListExample {

    public static void main(String[] args) {
        List<Person> plist1 = new ArrayList<>();
        List<Person> plist2 = new ArrayList<>();
        plist1.add(new Person(1, "11", "18"));
        plist1.add(new Person(2, "112", "19"));
        plist1.add(new Person(3, "113", "20"));
        plist2.add(new Person(1, "111", "18"));
        plist2.add(new Person(2, "112", "19"));
        plist2.add(new Person(3, "113", "20"));


//        List<Person> list = sameList(plist2, plist1);
        List<Person> list = diffList(plist1, plist2);

        for (Person person : list) {
            System.out.println(person.getName());
        }
    }

    /**
     * 取差集，注意入参顺序，保留first中在second不存在的数据
     * @Author created by barrett in 2023/2/3 15:25
     */
    private static List<Person> diffList(List<Person> firstArrayList, List<Person> secondArrayList) {
        List<Person> resultList = firstArrayList.stream()
                .filter(item -> !secondArrayList.stream().map(e -> e.getName() + "&" + e.getAge())
                        .collect(Collectors.toList()).contains(item.getName() + "&" + item.getAge()))
                .collect(Collectors.toList());
        return resultList;
    }

    /**
     * 取交集
     *
     * @Author created by barrett in 2023/2/3 15:13
     */
    public static List<Person> sameList(List<Person> oldArrayList, List<Person> newArrayList) {
        List<Person> resultList = newArrayList.stream()
                .filter(item -> oldArrayList.stream().map(e -> e.getName())
                        .collect(Collectors.toList()).contains(item.getName()))
                .collect(Collectors.toList());
        return resultList;
    }

    public void test() {
        String[] arrayA = new String[]{"1", "2", "3", "4"};

        String[] arrayB = new String[]{"3", "4", "5", "6"};
        List<String> listA = Arrays.asList(arrayA);
        List<String> listB = Arrays.asList(arrayB);

        //1、交集
        List<String> jiaoList = new ArrayList<>(listA);
        jiaoList.retainAll(listB);
        System.out.println(jiaoList);
        //输出:[3, 4]

        //2、差集
        List<String> chaList = new ArrayList<>(listA);
        chaList.removeAll(listB);
        System.out.println(chaList);
        //输出:[1, 2]

        //3、并集 (先做差集再做添加所有）
        List<String> bingList = new ArrayList<>(listA);
        bingList.removeAll(listB); // bingList为 [1, 2]
        bingList.addAll(listB);  //添加[3,4,5,6]
        System.out.println(bingList);
    }

}
