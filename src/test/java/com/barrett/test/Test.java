package com.barrett.test;

import com.barrett.util.download.DownLoad;


public class Test {

    public static void main(String[] args) {
        test2("overtime");
    }


    public static void test2(String str){
        switch (str){
            case "overtime":
                System.out.println("1");
            case "overtimeCheckId":
                System.out.println("2");
                break;
            default:
                System.out.println(3);
                break;
        }
    }

    public static void test1() {
        int random = (int) (Math.random() * 10);
        System.out.print(random);

        if (random < 5) {
            System.out.println("去");
        } else {
            System.out.println("暂时不去");
        }
    }

}
