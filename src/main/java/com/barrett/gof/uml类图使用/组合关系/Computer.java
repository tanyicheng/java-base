package com.barrett.gof.uml类图使用.组合关系;

/**
 * 如果Mouse,Monitor和Computer是不可分离的，则升级为组合关系
 * @author created by barrett in 2020/12/22 09:08
 **/
public class Computer {
	private Mouse mouse = new Mouse(); //鼠标可以和computer不能分离
	private Moniter moniter = new Moniter();//显示器可以和Computer不能分离
	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}
	public void setMoniter(Moniter moniter) {
		this.moniter = moniter;
	}
	
}
