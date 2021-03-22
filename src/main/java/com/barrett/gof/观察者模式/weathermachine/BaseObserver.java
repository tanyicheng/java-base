package com.barrett.gof.观察者模式.weathermachine;

/**
 * 抽象类 观察者
 *
 * @author created by barrett in 2021/3/22 22:15
 **/
public abstract class BaseObserver {
    protected Subject subject;

    /**
     * 执行工作
     * @author created by barrett in 2021/3/22 22:49
     **/
    public abstract void work();
}