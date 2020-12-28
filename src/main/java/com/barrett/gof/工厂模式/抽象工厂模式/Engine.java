package com.barrett.gof.工厂模式.抽象工厂模式;

/**
 * 产品类：发动机
 * @author created by barrett in 2020/12/28 13:43
 **/
public interface Engine {
 
}

//宝马的发动机
class EngineBMW implements Engine {
    public EngineBMW(){
        System.out.println("制造-->宝马发动机");
    }
}

//奔驰的发动机
class EngineBenz implements Engine {
    public EngineBenz(){
        System.out.println("制造-->奔驰发动机");
    }
}