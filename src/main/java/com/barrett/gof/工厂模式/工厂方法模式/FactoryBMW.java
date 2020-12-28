package com.barrett.gof.工厂模式.工厂方法模式;

/**
 * 工厂类
 * @author created by barrett in 2020/12/28 09:38
 **/
public interface FactoryBMW {
	BMW createBMW();
}
 
class FactoryBMW320 implements FactoryBMW{
 
	@Override
	public BMW320 createBMW() {
 
		return new BMW320();
	}
 
}

class FactoryBMW523 implements FactoryBMW {
	@Override
	public BMW523 createBMW() {

		return new BMW523();
	}
}
/**
 * todo 新建一个工厂类
 * @author created by barrett in 2020/12/28 09:42
 **/
class FactoryBMW500 implements FactoryBMW {

	@Override
	public BMW500 createBMW() {
		return new BMW500();
	}
}