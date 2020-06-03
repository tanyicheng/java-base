package com.barrett.gof.staticProxy;

/**
 * 静态代理
 * @Author created by barrett in 2020/6/3 16:32
 */
public class StaticProxy {

    public static void main(String[] args) {
        new WeddingCompany(new Person()).happyMarry();
    }
}

//结婚
interface Marry{

    void happyMarry();
}
/**
 * 真实角色
 * @Author created by barrett in 2020/6/3 16:36
 */
class Person implements Marry{

    @Override
    public void happyMarry() {
        System.out.println("愉快的结婚");
    }
}

/**
 * 婚庆公司
 * @Author created by barrett in 2020/6/3 16:37
 */
class WeddingCompany implements Marry{
    private Person target;//被代理的对象

    public WeddingCompany(Person target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        ready();
        target.happyMarry();
        after();
    }

    private void after() {
        System.out.println("婚礼结束后收尾");
    }

    private void ready() {
        System.out.println("婚礼前的准备");
    }
}