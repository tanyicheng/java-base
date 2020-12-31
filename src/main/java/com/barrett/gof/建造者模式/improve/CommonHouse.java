package com.barrett.gof.建造者模式.improve;

/**
 * 具体的产品
 * @author created by barrett in 2020/12/29 22:05
 **/
public class CommonHouse extends HouseBuilder {

	@Override
	public void basic() {
		// TODO Auto-generated method stub
		System.out.println(" 普通房子打地基5米 ");
	}

	@Override
	public void walls() {
		// TODO Auto-generated method stub
		System.out.println(" 普通房子砌墙10m ");
	}

	@Override
	public void roofed() {
		// TODO Auto-generated method stub
		System.out.println(" 普通房子屋顶 ");
	}

}
