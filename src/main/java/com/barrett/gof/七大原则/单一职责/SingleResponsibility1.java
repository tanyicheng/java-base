package com.barrett.gof.七大原则.单一职责;

/**
 * 单一职责1
 * @author created by barrett in 2020/12/12 21:01
 **/
public class SingleResponsibility1 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("小摩托");
        vehicle.run("汽车");
        vehicle.run("飞机");

    }


}

/**
 * 交通工具类
 * SingleResponsibility1
 * 1、TODO 在run方法中违反了单一职责原则
 * 2、解决方法：根据交通工具运行方法不同，分解成不同类即可
 * @author created by barrett in 2020/12/12 21:31
 **/
class Vehicle{

    public void run (String vehicle){

        System.out.println(vehicle+"在公路上运行...");
    }
}
