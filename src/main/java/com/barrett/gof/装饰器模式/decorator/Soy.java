package com.barrett.gof.装饰器模式.decorator;

/**
 * 豆浆
 * @author created by barrett in 2021/2/2 08:35
 **/
public class Soy extends Decorator{

	public Soy(Drink obj) {
		super(obj);
		// TODO Auto-generated constructor stub
		setDes(" 豆浆  ");
		setPrice(1.5f);
	}

}
