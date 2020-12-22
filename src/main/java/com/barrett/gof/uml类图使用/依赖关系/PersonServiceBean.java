package com.barrett.gof.uml类图使用.依赖关系;

/**
 * 只要是在类中用到了对方，那么他们之间就存在依赖关系。如果没有对方，连编
 * 绎都通过不了。
 * 小结
 * 1) 类中用到了对方
 * 2) 可以是类的成员属性
 * 3) 可以是方法的返回类型
 * 4) 可以是方法接收的参数类型
 * 5) 方法中使用到
 * @author created by barrett in 2020/12/22 09:06
 **/
public class PersonServiceBean {
	private PersonDao personDao;// 类

	public void save(Person person) {
	}

	public IDCard getIDCard(Integer personid) {
		return null;
	}

	public void modify() {
		Department department = new Department();
	}

	public static void main(String[] args) {

	}
}
