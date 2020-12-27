package com.barrett.gof.工厂模式.简单工厂.pizza;

//将Pizza 类做成抽象
public abstract class Pizza {
	protected String name; //名字

	//准备原材料, 不同的披萨不一样，因此，我们做成抽象方法
	public abstract void prepare();

	
	public void bake() {
		System.out.println(name + " 烘烤;");
	}

	public void cut() {
		System.out.println(name + " 切割;");
	}

	//打包
	public void box() {
		System.out.println(name + " 包装;");
	}

	public void setName(String name) {
		this.name = name;
	}
}
