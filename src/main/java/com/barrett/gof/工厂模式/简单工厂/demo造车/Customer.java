package com.barrett.gof.工厂模式.简单工厂.demo造车;

/**
 * 客户端调用
 * @author created by barrett in 2020/12/28 09:15
 **/
public class Customer {
	public static void main(String[] args) {
		Factory factory = new Factory();
		BMW bmw320 = factory.createBMW(320);
		BMW bmw523 = factory.createBMW(523);
	}
}