package com.barrett.gof.适配器模式.custom;


/**
 * 模拟各个系统的适配器
 * @author created by barrett in 2021/1/5 21:35
 **/
public interface HandlerAdapter {

    //判断是否支持这个对象
    public boolean supports(Object handler);

    //执行这个系统的软件
    public void handle(Object handler);
}


class AndroidHandlerAdapter implements HandlerAdapter {

    public void handle(Object handler) {
        ((AndroidCtrl) handler).doWorkAndroid();
    }

    public boolean supports(Object handler) {
        return (handler instanceof AndroidCtrl);
    }

}


class IOSHandlerAdapter implements HandlerAdapter {

    public void handle(Object handler) {
        ((IOSCtrl) handler).doWorkIOS();
    }

    public boolean supports(Object handler) {
        return (handler instanceof IOSCtrl);
    }

}


class HarmonyosHandlerAdapter implements HandlerAdapter {

    public void handle(Object handler) {
        ((HarmonyosCtrl) handler).doWorkHarmonyos();
    }

    public boolean supports(Object handler) {
        return (handler instanceof HarmonyosCtrl);
    }

}
