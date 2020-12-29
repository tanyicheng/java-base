package com.barrett.gof.七大原则.单一职责;

/**
 * 单一职责2
 * @author created by barrett in 2020/12/12 21:01
 **/
public class SingleResponsibility2 {
    public static void main(String[] args) {
        RoadVehicle ro = new RoadVehicle();
        ro.run("小摩托");
        ro.run("汽车");
        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");

    }


}

/**
 * 交通工具类
 * 1、遵守单一职责原则
 * 2、但是改动量很大，即将类分解，同事修改客户端
 * @author created by barrett in 2020/12/12 21:31
 **/
class RoadVehicle{

    public void run (String vehicle){

        System.out.println(vehicle+"在公路上运行...");
    }
}
class AirVehicle{

    public void run (String vehicle){

        System.out.println(vehicle+"在天空上运行...");
    }
}
class SeaVehicle{

    public void run (String vehicle){

        System.out.println(vehicle+"在海上上运行...");
    }
}
