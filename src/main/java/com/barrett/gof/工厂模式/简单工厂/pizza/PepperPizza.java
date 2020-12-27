package com.barrett.gof.工厂模式.简单工厂.pizza;

/**
 * 制作胡椒披萨
 * @author created by barrett in 2020/12/27 19:59
 **/
public class PepperPizza extends Pizza {

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		System.out.println(" 给胡椒披萨准备原材料 ");
	}

}
