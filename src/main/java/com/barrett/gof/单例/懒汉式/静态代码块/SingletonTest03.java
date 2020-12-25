package com.barrett.gof.单例.懒汉式.静态代码块;

/**
 * 懒汉式(线程不安全)
 * 1) 起到了Lazy Loading的效果，但是只能在单线程下使用。
 * 2) 如果在多线程下，一个线程进入了if (singleton == null)判断语句块，还未来得及
 * 往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例。所以
 * 在多线程环境下不可使用这种方式
 * 3) 结论：在实际开发中，不要使用这种方式.
 * @author created by barrett in 2020/12/24 22:12
 **/
public class SingletonTest03 {

	public static void main(String[] args) {
		System.out.println("懒汉式1 ， 线程不安全~");
		Singleton instance = Singleton.getInstance();
		Singleton instance2 = Singleton.getInstance();
		System.out.println(instance == instance2); // true
		System.out.println("instance.hashCode=" + instance.hashCode());
		System.out.println("instance2.hashCode=" + instance2.hashCode());
	}

}

class Singleton {
	private static Singleton instance;
	
	private Singleton() {}
	
	//提供一个静态的公有方法，当使用到该方法时，才去创建 instance
	//即懒汉式
	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
//	TODO 加同步锁，解决线程安全问题，但是貌似依旧有问题，待研究？
//	public static synchronized Singleton getInstance() {
//		if(instance == null) {
//			instance = new Singleton();
//		}
//		return instance;
//	}
}