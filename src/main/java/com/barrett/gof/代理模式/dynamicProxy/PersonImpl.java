package com.barrett.gof.代理模式.dynamicProxy;

/**
 * 具体实现
 * 接口：共有的方法，可以在抽象类中实现
 * 抽象类：共有的属性
 * 实现类：在以上基础扩展
 * @Author created by barrett in 2020/6/8 09:36
 */
public class PersonImpl  {
    public static void main(String[] args) throws Exception {
        String str ="";
        ManPerson m = ManPerson.class.newInstance();
//        WomenPerson m= WomenPerson.class.newInstance();
        m.name="张三";
        m.age=99;
        m.voice("牛肉");
        System.out.println(m);

    }
}

class ManPerson extends Person {

    @Override
    public String eat(String food){
        return null;
    }

    @Override
    public void voice(String msg) {
        System.out.println("男人说："+msg);
    }

    @Override
    public String toString() {
        return "ManPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class WomenPerson extends Person{

    @Override
    public void voice(String msg) {
        System.out.println("女人说："+msg);
    }
}