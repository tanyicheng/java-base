package com.barrett.gof.适配器模式.类适配器;

public class Phone {

	//充电
	public void charging(IVoltage iVoltage) {
		System.out.println("转换后的电压为："+iVoltage.outputChange());

	}
}
