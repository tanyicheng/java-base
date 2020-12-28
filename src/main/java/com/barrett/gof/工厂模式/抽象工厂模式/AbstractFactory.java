package com.barrett.gof.工厂模式.抽象工厂模式;

/**
 * 工厂类
 * @author created by barrett in 2020/12/28 13:45
 **/
public interface AbstractFactory {  
    //制造发动机
    public Engine createEngine();
    //制造空调 
    public Aircondition createAircondition(); 
}


//为宝马系列生产配件
class FactoryBMW implements AbstractFactory{

    public FactoryBMW() {
        System.out.println("构建宝马工厂---");
    }

    @Override
    public Engine createEngine() {
        return new EngineBMW();
    }
    @Override
    public Aircondition createAircondition() {
        return new AirconditionBMW();
    }
}

//奔驰车生产配件
class FactoryBenz implements AbstractFactory {
    public FactoryBenz() {
        System.out.println("构建奔驰工厂---");
    }

    @Override
    public Engine createEngine() {
        return new EngineBenz();
    }

    @Override
    public Aircondition createAircondition() {
        return new AirconditionBenz();
    }


}