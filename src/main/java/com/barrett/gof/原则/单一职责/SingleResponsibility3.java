package com.barrett.gof.原则.单一职责;

/**
 * 单一职责3
 * 1) 降低类的复杂度，一个类只负责一项职责。
 * 2) 提高类的可读性，可维护性
 * 3) 降低变更引起的风险
 * 4) 通常情况下，我们应当遵守单一职责原则，只有逻辑足够简单，才可以在代码级违
 * 反单一职责原则；只有类中方法数量足够少，可以在方法级别保持单一职责原则
 *
 * @author created by barrett in 2020/12/12 21:01
 **/
public class SingleResponsibility3 {
    public static void main(String[] args) {
        Vehicle3 ro = new Vehicle3();
        ro.run("小摩托");
        ro.run("汽车");
        ro.runAir("飞机");

    }


}

/**
 * 交通工具类
 * 1、没有方式2的大量修改，只是增加方法
 * 2、这里虽然没有在类这个级别上遵守单一职责原则，但是在方法级别上，仍然遵守单一职责
 *
 * @author created by barrett in 2020/12/12 21:31
 **/
class Vehicle3 {

    public void run(String vehicle) {
        System.out.println(vehicle + "在公路上运行...");
    }

    public void runAir(String vehicle) {
        System.out.println(vehicle + "在天空上运行...");
    }

    public void runWater(String vehicle) {
        System.out.println(vehicle + "在水上运行...");
    }
}

