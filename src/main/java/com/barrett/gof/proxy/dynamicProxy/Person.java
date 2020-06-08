package com.barrett.gof.proxy.dynamicProxy;

/**
 * 人的基本属性
 * @Author created by barrett in 2020/6/8 09:17
 */
public abstract class Person implements IPersion{
    String name;
    int age;

    @Override
    public String eat(String food) {
        System.out.println("每天都要吃："+food);
        return food;
    }

}

