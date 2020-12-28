package com.barrett.gof.工厂模式.抽象工厂模式;
/**
 * 产品类：空调
 * @author created by barrett in 2020/12/28 13:47
 **/
public interface Aircondition {  
 
}

//宝马的空调
class AirconditionBMW implements Aircondition {
    public AirconditionBMW(){
        System.out.println("制造-->宝马空调");
    }
}

//奔驰的空调
class AirconditionBenz implements Aircondition {
    public AirconditionBenz(){
        System.out.println("制造-->奔驰空调");
    }
}