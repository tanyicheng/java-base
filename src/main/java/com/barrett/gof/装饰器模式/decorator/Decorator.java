package com.barrett.gof.装饰器模式.decorator;

/**
 * 装饰器：调味品 也算饮料的一种
 * @author created by barrett in 2021/2/1 22:17
 **/
public class Decorator extends Drink {

	private Drink obj;

	/**
	 * 组合饮料，无限套娃模式。。。有点像递归
	 * @author created by barrett in 2021/2/1 22:17
	 **/
	public Decorator(Drink obj) {
		// TODO Auto-generated constructor stub
		this.obj = obj;
	}
	
	@Override
	public float cost() {
		// TODO Auto-generated method stub
		// getPrice 自己价格 + 传递进来的饮品价格（被装饰者）
		return super.getPrice() + obj.cost();
	}
	
	@Override
	public String getDes() {
		// TODO Auto-generated method stub
		// obj.getDes() 输出被装饰者的信息
		return des + " " + getPrice() + " && " + obj.getDes();
	}
	
	

}
