package com.barrett.gof.适配器模式.类适配器;

/**
 * 1) Java是单继承机制，所以类适配器需要继承src类这一点算是一个缺点, 因为这要
 * 求dst必须是接口，有一定局限性;
 * 2) src类的方法在Adapter中都会暴露出来，也增加了使用的成本。
 * 3) 由于其继承了src类，所以它可以根据需求重写src类的方法，使得Adapter的灵
 * 活性增强了。
 * @author created by barrett in 2021/1/2 21:20
 **/
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" === 类适配器模式 ====");
		Phone phone = new Phone();
		phone.charging(new VoltageAdapter5());

	}

}
