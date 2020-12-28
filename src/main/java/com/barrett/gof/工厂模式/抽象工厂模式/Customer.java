package com.barrett.gof.工厂模式.抽象工厂模式;

import org.apache.commons.math3.analysis.function.Abs;

/**
 * 客户端调用
 * 抽象工厂生产的是一个产品族
 * 不同的汽车品牌，产品配件型号不同
 * @author created by barrett in 2020/12/28 13:50
 **/
public class Customer {

    public static void main(String[] args){
        //方法一
//        fun1();

        //方法二
        fun2();
    }


    public static void fun1(){
        //生产宝马系列配件
        FactoryBMW factoryBMW = new FactoryBMW();
        factoryBMW.createEngine();
        factoryBMW.createAircondition();

        //生产奔驰配件
        FactoryBenz factoryBenz = new FactoryBenz();
        factoryBenz.createEngine();
        factoryBenz.createAircondition();
    }


    AbstractFactory factory = null;

    public Customer(String name) {
        setFactory(name);
    }

    public void setFactory(String name) {
        if(name.equals("bmw")){
            factory = new FactoryBMW();
        }else if(name.equals("benz")){
            factory = new FactoryBenz();
        }

        //生产指定品牌的配件
        factory.createEngine();
        factory.createAircondition();
    }


    public static void fun2(){
        //根据客户指定品牌，生产一个产品族
        Customer c = new Customer("bmw");

//        c.factory.createEngine(); //不应该放在调用方，实际情况按需
//        c.factory.createAircondition();
    }

}