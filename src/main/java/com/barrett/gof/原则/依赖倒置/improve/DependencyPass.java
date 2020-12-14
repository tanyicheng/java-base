package com.barrett.gof.原则.依赖倒置.improve;

/**
 * TODO-demo
 * 1) 低层模块尽量都要有抽象类或接口，或者两者都有，程序稳定性更好.
 * 2) 变量的声明类型尽量是抽象类或接口, 这样我们的变量引用和实际对象间，就存在
 * 一个缓冲层，利于程序扩展和优化
 * 3) 继承时遵循里氏替换原则
 * @author created by barrett in 2020/12/14 21:41
 **/
public class DependencyPass {

    public static void main(String[] args) {
        ChangHong changHong = new ChangHong();
        //方式一：
//		OpenAndClose openAndClose = new OpenAndClose();
//		openAndClose.open(changHong);

        //方式二：通过构造器进行依赖传递
		OpenAndClose openAndClose = new OpenAndClose(changHong);
		openAndClose.open();

        //方式三：通过setter方法进行依赖传递
//        openAndClose.setTv(changHong);
//        openAndClose.open();

    }

}

// 方式1： 通过接口传递实现依赖
// 开关的接口
//interface IOpenAndClose {
//    public void open(ITV tv); //抽象方法,接收接口
//}
//
//// 实现接口
//class OpenAndClose implements IOpenAndClose {
//    public void open(ITV tv) {
//        tv.play();
//    }
//}

// 方式2: 通过构造方法依赖传递
interface IOpenAndClose {
	public void open(); //抽象方法
}
class OpenAndClose implements IOpenAndClose {
	public ITV tv; //成员

	public OpenAndClose(ITV tv) { //构造器
		this.tv = tv;
	}

	public void open() {
		this.tv.play();
	}
}


// 方式3 , 通过setter方法传递
//interface IOpenAndClose {
//    public void open(); // 抽象方法
//    public void setTv(ITV tv);
//}
//class OpenAndClose implements IOpenAndClose {
//    private ITV tv;
//    public void setTv(ITV tv) {
//        this.tv = tv;
//    }
//    public void open() {
//        this.tv.play();
//    }
//}

interface ITV { // ITV接口
	void play();
}

class ChangHong implements ITV {
    @Override
    public void play() {
        // TODO Auto-generated method stub
        System.out.println("长虹电视机，打开");
    }

}