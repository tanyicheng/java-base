package com.barrett.gof.策略模式.mes;

/**
 * 产线各个站点
 * @author created by barrett in 2021/6/27 21:04
 **/
public abstract class Site {

    //通用的刷入
    void commonBrush(){
        System.out.println("公共的刷入逻辑");
    }

    protected abstract void preBrush();
}
