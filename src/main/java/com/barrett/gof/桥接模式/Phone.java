package com.barrett.gof.桥接模式;

/**
 * 抽象类-手机
 *
 * @author created by barrett in 2021/1/25 20:26
 **/
public abstract class Phone {

    //聚合品牌
    private Brand brand;

    /**
     * 构建手机的同时构建品牌
     *
     * @author created by barrett in 2021/1/25 20:28
     **/
    public Phone(Brand brand) {
        super();
        this.brand = brand;
    }


    protected void open() {
        this.brand.open();
    }

    protected void close() {
        this.brand.close();
    }

    protected void call() {
        this.brand.call();
    }

}
