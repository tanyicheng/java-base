package com.barrett.gof.建造者模式.improve;

/**
 * 建造者模式的四个角色：
	 1) Product（产品角色）： 一个具体的产品对象。
	 2) Builder（抽象建造者）： 创建一个Product对象的各个部件指定的 接口/抽象类。
	 3) ConcreteBuilder（具体建造者）： 实现接口，构建和装配各个部件。
	 4) Director（指挥者）： 构建一个使用Builder接口的对象。它主要是用于创建一个
	 复杂的对象。它主要有两个作用，一是：隔离了客户与对象的生产过程，二是：
	 负责控制产品对象的生产过程。
 * @author created by barrett in 2020/12/29 21:54
 **/
public class Main {
	public static void main(String[] args) {
		
		//盖普通房子
		CommonHouse commonHouse = new CommonHouse();
		//准备创建房子的指挥者
		HouseDirector houseDirector = new HouseDirector(commonHouse);
		
		//完成盖房子，返回产品(普通房子)
		House house = houseDirector.constructHouse();
		
		//System.out.println("输出流程");
		
		System.out.println("--------------------------");

		//盖高楼，重置建造者
		houseDirector.setHouseBuilder(new HighBuilding());
		//完成盖房子，返回产品(高楼)
		houseDirector.constructHouse();
		
		
		
	}
}
