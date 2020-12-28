package com.barrett.gof.工厂模式.工厂方法模式;

/**
 * 产品类
 * @author created by barrett in 2020/12/28 09:37
 **/
public abstract class BMW {
	public BMW(){
		
	}
}
class BMW320 extends BMW {
	public BMW320() {
		System.out.println("制造-->BMW320");
	}
}

class BMW523 extends BMW{
	public BMW523(){
		System.out.println("制造-->BMW523");
	}
}
/**
 * todo 新增一个产品
 * @author created by barrett in 2020/12/28 09:42
 **/
class BMW500 extends BMW{
	public BMW500(){
		System.out.println("制造-->BMW500");
	}
}