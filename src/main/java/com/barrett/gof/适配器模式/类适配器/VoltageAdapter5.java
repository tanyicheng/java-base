package com.barrett.gof.适配器模式.类适配器;

//适配器类
public class VoltageAdapter5 extends Voltage220V implements IVoltage {

	/**
	 * 将220v转换为5v输出
	 * @author created by barrett in 2021/1/2 21:08
	 **/
	@Override
	public int outputChange() {
		// TODO Auto-generated method stub
		//获取到220V电压
		int srcV = output220V();
		return srcV / 44;
	}

}
