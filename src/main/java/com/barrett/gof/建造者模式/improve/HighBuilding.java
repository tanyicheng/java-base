package com.barrett.gof.建造者模式.improve;

/**
 * 具体子类实现（具体的产品）
 * @author created by barrett in 2020/12/29 22:05
 **/
public class HighBuilding extends HouseBuilder {

	@Override
	public void basic() {
		// TODO Auto-generated method stub
		System.out.println(" 高楼的打地基100米 ");
	}

	@Override
	public void walls() {
		// TODO Auto-generated method stub
		System.out.println(" 高楼的砌墙20m ");
	}

	@Override
	public void roofed() {
		// TODO Auto-generated method stub
		System.out.println(" 高楼的透明屋顶 ");
	}

}
