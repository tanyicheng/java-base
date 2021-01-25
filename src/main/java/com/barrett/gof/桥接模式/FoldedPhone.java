package com.barrett.gof.桥接模式;


/**
 * 折叠手机
 * @author created by barrett in 2021/1/25 20:31
 **/
public class FoldedPhone extends Phone {

	//构建折叠手机，赋予品牌
	public FoldedPhone(Brand brand) {
		super(brand);
	}

	@Override
	public void open() {
		super.open();
		System.out.println("折叠手机 open");
	}

	@Override
	public void close() {
		super.close();
		System.out.println("折叠手机 close");
	}

	@Override
	public void call() {
		super.call();
		System.out.println("折叠手机 call");
	}
}
