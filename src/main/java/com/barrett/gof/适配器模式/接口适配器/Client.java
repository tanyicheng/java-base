package com.barrett.gof.适配器模式.接口适配器;

/**
 * 1) 一些书籍称为：适配器模式(Default Adapter Pattern)或缺省适配器模式。
 * 2) 当不需要全部实现接口提供的方法时，可先设计一个抽象类实现接口，并为该接
 * 口中每个方法提供一个默认实现（空方法），那么该抽象类的子类可有选择地覆
 * 盖父类的某些方法来实现需求
 * 3) 适用于一个接口不想使用其所有的方法的情况。
 *
 * 接口适配器模式应用实例
 * 1) Android中的属性动画ValueAnimator类可以
 * 通过addListener(AnimatorListener listener)方
 * 法添加监听器， 那么常规写法如右：
 * 2) 有时候我们不想实现
 * Animator.AnimatorListener接口的全部方法， 我们只想监听onAnimationStart，我们会如 下写
 * @author created by barrett in 2021/1/5 20:22
 **/
public class Client {
	public static void main(String[] args) {
		
		AbsAdapter absAdapter = new AbsAdapter() {
			//只需要去覆盖我们 需要使用 接口方法
			@Override
			public void m1() {
				// TODO Auto-generated method stub
				System.out.println("使用了m1的方法");
			}
		};
		
		absAdapter.m1();

	}
}
