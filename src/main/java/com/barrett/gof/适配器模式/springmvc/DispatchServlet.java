package com.barrett.gof.适配器模式.springmvc;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器模式在SpringMVC框架应用的源码剖析
 * 1) SpringMvc中的HandlerAdapter, 就使用了适配器模式
 * 2) SpringMVC处理请求的流程回顾
 * 3) 使用HandlerAdapter 的原因分析:
 * 可以看到处理器的类型不同，有多重实现方式，那么调用方式就不是确定的，如果需要直接调用
 * Controller方法，需要调用的时候就得不断是使用if else来进行判断是哪一种子类然后执行。那么
 * 如果后面要扩展Controller，就得修改原来的代码，这样违背了OCP原则。
 *
 * 说明：
 * • Spring定义了一个适配接口，使得每一种Controller有一种对应的适配器实现类
 * • 适配器代替controller执行相应的方法
 * • 扩展Controller 时，只需要增加一个适配器类就完成了SpringMVC的扩展了,
 * • 这就是设计模式的力量
 * @author created by barrett in 2021/1/5 20:45
 **/
public class DispatchServlet {

	public static List<HandlerAdapter> handlerAdapters = new ArrayList<HandlerAdapter>();

	public DispatchServlet() {
		handlerAdapters.add(new AnnotationHandlerAdapter());
		handlerAdapters.add(new HttpHandlerAdapter());
		handlerAdapters.add(new SimpleHandlerAdapter());
	}

	public static void main(String[] args) {
		new DispatchServlet().doDispatch(); // http...
	}

	public void doDispatch() {

		// 此处模拟SpringMVC从request取handler的对象，
		// 适配器可以获取到希望的Controller
		 HttpController controller = new HttpController();
		// AnnotationController controller = new AnnotationController();
		//SimpleController controller = new SimpleController();
		// 得到对应适配器
		HandlerAdapter adapter = getHandler(controller);
		// 通过适配器执行对应的controller对应方法
		adapter.handle(controller);

	}

	public HandlerAdapter getHandler(Controller controller) {
		//遍历：根据得到的controller(handler), 返回对应适配器
		for (HandlerAdapter adapter : this.handlerAdapters) {
			if (adapter.supports(controller)) {
				return adapter;
			}
		}
		return null;
	}


}
