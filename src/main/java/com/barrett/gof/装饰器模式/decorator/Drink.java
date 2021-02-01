package com.barrett.gof.装饰器模式.decorator;

/**
 * 抽象类，最终组合的饮料
 * 子类具体实现价格计算
 * @author created by barrett in 2021/2/1 22:14
 **/
public abstract class Drink {

    public String des; // 描述
    //饮料的价格
    private float price = 0.0f;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    //计算费用的抽象方法
    //子类来实现
    public abstract float cost();

}
