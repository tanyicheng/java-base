package com.barrett.gof.装饰器模式.decorator;

/**
 * 意大利咖啡
 * @author created by barrett in 2021/2/1 22:13
 **/
public class Espresso extends Coffee {
	
	public Espresso() {
		setDes(" 意大利咖啡 ");
		setPrice(6.0f);
	}
}
