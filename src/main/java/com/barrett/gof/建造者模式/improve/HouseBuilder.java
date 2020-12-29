package com.barrett.gof.建造者模式.improve;


// （产品）抽象的建造者
public abstract class HouseBuilder {

	protected House house = new House();
	
	//将建造的流程写好, 抽象的方法
	public abstract void basic();
	public abstract void walls();
	public abstract void roofed();
	
	//建造房子好， 将产品(房子) 返回
	public House build() {
		return house;
	}
	
}
