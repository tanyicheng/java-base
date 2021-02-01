package com.barrett.gof.装饰器模式.decorator;

/**
 * 具体的饮料->咖啡制作->提取出公共的方法
 *
 * @author created by barrett in 2021/2/1 22:13
 **/
public class Coffee extends Drink {

    /**
     * 咖啡的价格计算
     * @author created by barrett in 2021/2/1 22:16
     **/
    @Override
    public float cost() {
        // TODO Auto-generated method stub
        return super.getPrice();
    }


}
