package com.barrett.test;

public class Test {

    public static void main(String[] args) {
        test1();
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
