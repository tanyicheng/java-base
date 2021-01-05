package com.barrett.gof.适配器模式.custom;

/**
 * 模拟各个系统的控制器
 *
 * @author created by barrett in 2021/1/5 21:31
 **/
public interface Controller {

}

/**
 * 安卓
 *
 * @author created by barrett in 2021/1/5 21:30
 **/
class AndroidCtrl implements Controller {
    public void doWorkAndroid() {
        System.out.println("doAndroid...");
    }
}

/**
 * 苹果
 *
 * @author created by barrett in 2021/1/5 21:31
 **/
class IOSCtrl implements Controller {
    public void doWorkIOS() {
        System.out.println("doWorkIOS...");
    }
}

/**
 * 鸿蒙
 *
 * @author created by barrett in 2021/1/5 21:31
 **/
class HarmonyosCtrl implements Controller {
    public void doWorkHarmonyos() {
        System.out.println("doWorkHarmonyos...");
    }
}
