package com.barrett.gof.原则.开闭原则;

/**
 * 1) 开闭原则（Open Closed Principle）是编程中最基础、最重要的设计原则
 * 2) 一个软件实体如类，模块和函数应该对扩展开放(对提供方)，对修改关闭(对使用
 * 方)。用抽象构建框架，用实现扩展细节。
 * 3) 当软件需要变化时，尽量通过扩展软件实体的行为来实现变化，而不是通过修改已
 * 有的代码来实现变化。
 * 4) 编程中遵循其它原则，以及使用设计模式的目的就是遵循开闭原则
 * @author created by barrett in 2020/12/15 21:39
 **/
public class Ocp {

	public static void main(String[] args) {
		//使用看看存在的问题
		GraphicEditor graphicEditor = new GraphicEditor();
		graphicEditor.drawShape(new Rectangle());
		graphicEditor.drawShape(new Circle());
		//新增的这个类违背了开闭原则
		graphicEditor.drawShape(new Triangle());
	}

}

//这是一个用于绘图的类 [使用方]
class GraphicEditor {
	//接收Shape对象，然后根据type，来绘制不同的图形
	public void drawShape(Shape s) {
		if (s.m_type == 1)
			drawRectangle(s);
		else if (s.m_type == 2)
			drawCircle(s);
		//TODO 这里需要新增
		else if (s.m_type == 3)
			drawTriangle(s);
	}

	//绘制矩形
	public void drawRectangle(Shape r) {
		System.out.println(" 绘制矩形 ");
	}

	//绘制圆形
	public void drawCircle(Shape r) {
		System.out.println(" 绘制圆形 ");
	}

	//TODO 这里需要新增绘制三角形
	public void drawTriangle(Shape r) {
		System.out.println(" 绘制三角形 ");
	}
}

//Shape类，基类
class Shape {
	int m_type;
}

class Rectangle extends Shape {
	Rectangle() {
		super.m_type = 1;
	}
}

class Circle extends Shape {
	Circle() {
		super.m_type = 2;
	}
}

//TODO 新增画三角形
class Triangle extends Shape {
	Triangle() {
		super.m_type = 3;
	}

}
