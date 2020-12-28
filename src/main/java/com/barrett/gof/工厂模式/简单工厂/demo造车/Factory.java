package com.barrett.gof.工厂模式.简单工厂.demo造车;

/**
 * 简单工厂类
 *
 * @author created by barrett in 2020/12/28 09:13
 **/
public class Factory {
    //统一的创建产品入口
    public BMW createBMW(int type) {
        switch (type) {

            case 320:
                return new BMW320();

            case 523:
                return new BMW523();

            default:
                break;
        }
        return null;
    }
}
