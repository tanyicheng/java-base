package com.barrett.gof.桥接模式;
/**
 * 小米品牌
 * 品牌下拥有的功能
 * @author created by barrett in 2021/1/25 20:35
 **/
public class Vivo implements Brand {

	@Override
	public void open() {
		// TODO Auto-generated method stub
		System.out.println(" Vivo 手机 open ");
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println(" Vivo 手机 close ");
	}

	@Override
	public void call() {
		// TODO Auto-generated method stub
		System.out.println(" Vivo 手机 call");
	}

}
