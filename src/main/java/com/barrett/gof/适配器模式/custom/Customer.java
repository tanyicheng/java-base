package com.barrett.gof.适配器模式.custom;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用者
 * 手机各个系统之间的应用无法相互调用
 * customer 通过哪个系统（ios A.. H.）调用该系统的应用
 * @author created by barrett in 2021/1/5 21:39
 **/
public class Customer {

    //收集所有系统
    public static List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    public Customer(){
        handlerAdapters.add(new AndroidHandlerAdapter());
        handlerAdapters.add(new IOSHandlerAdapter());
        handlerAdapters.add(new HarmonyosHandlerAdapter());
    }

    public HandlerAdapter getHandler(Controller ctrl){

        for (HandlerAdapter adapter : handlerAdapters) {
            //如果存在这个系统则返回改系统的对象
            if(adapter.supports(ctrl)){
                return adapter;
            }
        }
        return null;
    }

    //使用调用
    public void doWork(){
        //建立ios系统
        Controller ctrl = new IOSCtrl();

        //通过适配器找到ios系统的软件
        HandlerAdapter adapter = getHandler(ctrl);
        //调用指定系统的软件
        adapter.handle(ctrl);
    }

    public static void main(String[] args) {
        new Customer().doWork();
    }
}
