package com.barrett.gof.composite;

/**
 * 系 叶子节点
 * @author created by barrett in 2021/2/3 22:09
 **/
public class Department extends OrganizationComponent {

	//没有集合
	
	public Department(String name, String des) {
		super(name, des);
		// TODO Auto-generated constructor stub
	}

	
	//add , remove 就不用写了，因为他是叶子节点
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}
	
	@Override
	public String getDes() {
		// TODO Auto-generated method stub
		return super.getDes();
	}
	
	@Override
	protected void print() {
		// TODO Auto-generated method stub
		System.out.println(getName());
	}

}
