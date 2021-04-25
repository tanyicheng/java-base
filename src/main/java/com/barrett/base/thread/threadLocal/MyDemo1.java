package com.barrett.base.thread.threadLocal;

/**
 * 需求:
 *      程隔在多线程开发的场景下,每个线程中的变量都是相互独立
 *      线程A: 设置(变量1) 获取(变量1)
 *      线程B: 设置(变量2) 获取(变量2)
 *
 *      ThreadLocal:
 *          1. set() : 将变量绑定到当前线程中
 *          2. get() :获取当前线程绑定的变量
 *
 * 从结果来看，这样很好的解决了多线程之间数据隔离的问题
 * @author created by barrett in 2021/4/25 22:07
 **/
public class MyDemo1 {

    private static ThreadLocal<String> tl = new ThreadLocal<>();

    private String content;

    private String getContent() {
        return tl.get();
    }

    private void setContent(String content) {
         tl.set(content);
    }

    public static void main(String[] args) {
        MyDemo1 demo = new MyDemo1();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.setContent(Thread.currentThread().getName() + "的数据");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("-----------------------");
                    System.out.println(Thread.currentThread().getName() + "--->" + demo.getContent());
                }
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }
}