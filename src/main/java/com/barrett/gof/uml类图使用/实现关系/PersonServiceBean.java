package com.barrett.gof.uml类图使用.实现关系;

/**
 * 实现关系实际上就是A类实现B接口
 * @author created by barrett in 2020/12/22 09:06
 **/
public class PersonServiceBean implements PersonService{

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("delete..");
	}

}
