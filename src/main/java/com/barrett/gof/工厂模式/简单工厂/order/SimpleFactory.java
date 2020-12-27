package com.barrett.gof.工厂模式.简单工厂.order;


import com.barrett.gof.工厂模式.简单工厂.pizza.CheesePizza;
import com.barrett.gof.工厂模式.简单工厂.pizza.GreekPizza;
import com.barrett.gof.工厂模式.简单工厂.pizza.PepperPizza;
import com.barrett.gof.工厂模式.简单工厂.pizza.Pizza;

/**
 * 简单工厂类
 * 1) 简单工厂模式是属于创建型模式，是工厂模式的一种。简单工厂模式是由一
 * 个工厂对象决定创建出哪一种产品类的实例。简单工厂模式是工厂模式家族
 * 中最简单实用的模式
 * 2) 简单工厂模式：定义了一个创建对象的类，由这个类来封装实例化对象的行
 * 为(代码)
 * 3) 在软件开发中，当我们会用到大量的创建某种、某类或者某批对象时，就会
 * 使用到工厂模式.
 * 简单工厂模式的设计方案: 定义一个可以实例化Pizaa对象的类，封装创建对象的代码。
 * @author created by barrett in 2020/12/27 22:36
 **/
public class SimpleFactory {

	//更加orderType 返回对应的Pizza 对象
	public Pizza createPizza(String orderType) {

		Pizza pizza = null;

		System.out.println("使用简单工厂模式");
		if (orderType.equals("greek")) {
			pizza = new GreekPizza();
			pizza.setName(" 希腊披萨 ");
		} else if (orderType.equals("cheese")) {
			pizza = new CheesePizza();
			pizza.setName(" 奶酪披萨 ");
		} else if (orderType.equals("pepper")) {
			pizza = new PepperPizza();
			pizza.setName("胡椒披萨");
		}
		
		return pizza;
	}
	
	//简单工厂模式 也叫 静态工厂模式 
	
	public static Pizza createPizza2(String orderType) {

		Pizza pizza = null;

		System.out.println("使用简单工厂模式2");
		if (orderType.equals("greek")) {
			pizza = new GreekPizza();
			pizza.setName(" 希腊披萨 ");
		} else if (orderType.equals("cheese")) {
			pizza = new CheesePizza();
			pizza.setName(" 奶酪披萨 ");
		} else if (orderType.equals("pepper")) {
			pizza = new PepperPizza();
			pizza.setName("胡椒披萨");
		}
		
		return pizza;
	}

}
