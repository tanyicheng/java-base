package com.barrett.gof.原则.依赖倒置.improve;

/**
 * 依赖倒转原则(Dependence Inversion Principle)是指：
 * 1) 高层模块不应该依赖低层模块，二者都应该依赖其抽象
 * 2) 抽象不应该依赖细节，细节应该依赖抽象
 * 3) 依赖倒转(倒置)的中心思想是面向接口编程
 * 4) 依赖倒转原则是基于这样的设计理念：相对于细节的多变性，抽象的东西要稳定的
 * 多。以抽象为基础搭建的架构比以细节为基础的架构要稳定的多。在java中，抽象
 * 指的是接口或抽象类，细节就是具体的实现类
 * 5) 使用接口或抽象类的目的是制定好规范，而不涉及任何具体的操作，把展现细节的
 * 任务交给他们的实现类去完成
 * @author created by barrett in 2020/12/14 21:53
 **/
public class DependecyInversion {

	public static void main(String[] args) {
		//客户端无需改变
		Person person = new Person();
		person.receive(new Email());

		person.receive(new WeiXin());
	}

}

//定义接口
interface IReceiver {
	public String getInfo();
}

class Email implements IReceiver {
	public String getInfo() {
		return "电子邮件信息: hello,world";
	}
}

//增加微信
class WeiXin implements IReceiver {
	public String getInfo() {
		return "微信信息: hello,ok";
	}
}

//方式2
class Person {
	//这里我们是对接口的依赖
	public void receive(IReceiver receiver ) {
		System.out.println(receiver.getInfo());
	}
}
