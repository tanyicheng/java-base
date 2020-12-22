package com.barrett.gof.uml类图使用.聚合关系;

/**
 * 聚合关系（Aggregation）表示的是整体和部分的关系，整体与部分可以分开。聚
 * 合关系是关联关系的特例，所以他具有关联的导航性与多重性。
 * 如：一台电脑由键盘(keyboard)、显示器(monitor)，鼠标等组成；组成电脑的各个
 * 配件是可以从电脑上分离出来的，使用带空心菱形的实线来表示
 * @author created by barrett in 2020/12/22 09:07
 **/
public class Computer {
	private Mouse mouse; //鼠标可以和computer分离
	private Moniter moniter;//显示器可以和Computer分离
	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}
	public void setMoniter(Moniter moniter) {
		this.moniter = moniter;
	}
	
}
