package com.barrett.gof.decorator;

/**
 * 装饰器(咖啡)
 * 1、抽象组件：需要装饰的抽象对象（接口或抽象类）
 * 2、具体组件：需要装饰的对象
 * 3、抽象装饰类：包含了对象的组件的引用以及装饰着共有的方法
 * 4、具体装饰类：被装饰的对象
 * @Author created by barrett in 2020/5/17 10:04
 */
public class DecoratorDemo {
    public static void main(String[] args) {

        Drink drink = new Coffee();
        System.out.println(drink.desc()+"   价格："+drink.cost());

        Drink milk = new Milk(drink);
        System.out.println(milk.desc()+"   价格："+milk.cost());

        Drink sugar= new Sugar(milk);
        System.out.println(sugar.desc()+"   价格："+sugar.cost());




    }


}
//1、抽象组件：饮料
interface Drink{
    //费用
    double cost();

    //说明
    String desc();

}

//2、具体组件：咖啡
class Coffee implements Drink{

    private String name = "原味咖啡";
    //原味咖啡：10元
    public double cost() {
        return 10;
    }

    public String desc() {
        return name;
    }
}
//3、抽象装饰类
abstract class Decorator implements Drink{

    //设置引用
    private Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    public double cost() {
        return this.drink.cost();
    }

    public String desc() {
        return this.drink.desc();
    }
}
//4、具体装饰类
class Milk extends Decorator{

    public Milk(Drink drink) {
        super(drink);
    }

    public double cost() {
        return super.cost()+8;
    }

    public String desc() {
        return super.desc()+"加入牛奶";
    }
}
//4、具体装饰类
class Sugar extends Decorator{

    public Sugar(Drink drink) {
        super(drink);
    }

    public double cost() {
        return super.cost()+3;
    }

    public String desc() {
        return super.desc()+"加入白沙糖";
    }
}