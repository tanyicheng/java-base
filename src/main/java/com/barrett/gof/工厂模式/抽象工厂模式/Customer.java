package com.barrett.gof.工厂模式.抽象工厂模式;

import org.apache.commons.math3.analysis.function.Abs;

/**
 * 客户端调用
 * 抽象工厂生产的是一个产品族
 * 不同的汽车品牌，产品配件型号不同
 *
 * 1) 抽象工厂模式：定义了一个interface用于创建相关或有依赖关系的对象簇，而无需
 * 指明具体的类
 * 2) 抽象工厂模式可以将简单工厂模式和工厂方法模式进行整合。
 * 3) 从设计层面看，抽象工厂模式就是对简单工厂模式的改进(或者称为进一步的抽象)。
 * 4) 将工厂抽象成两层，AbsFactory(抽象工厂) 和 具体实现的工厂子类。程序员可以
 * 根据创建对象类型使用对应的工厂子类。这样将单个的简单工厂类变成了工厂簇，
 * 更利于代码的维护和扩展。
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