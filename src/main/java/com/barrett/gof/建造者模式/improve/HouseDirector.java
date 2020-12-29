package com.barrett.gof.建造者模式.improve;

//指挥者，这里去指定制作流程，返回产品
public class HouseDirector {
	
	HouseBuilder houseBuilder = null;

	//方式一：构造器传入 houseBuilder
	public HouseDirector(HouseBuilder houseBuilder) {
		this.houseBuilder = houseBuilder;
	}

	//方式二：通过setter 传入 houseBuilder
	public void setHouseBuilder(HouseBuilder houseBuilder) {
		this.houseBuilder = houseBuilder;
	}
	
	//如何处理建造房子的流程，交给指挥者，返回构建完成的产品
	public House constructHouse() {
		houseBuilder.basic();
		houseBuilder.walls();
		houseBuilder.roofed();
		return houseBuilder.build();
	}
	
	
}
