package com.barrett.gof.原型模式.浅拷贝;


/**
 * 浅拷贝
 * 1) 原型模式(Prototype模式)是指：用原型实例指定创建对象的种类，并且通过拷
 * 贝这些原型，创建新的对象
 * 2) 原型模式是一种创建型设计模式，允许一个对象再创建另外一个可定制的对象， 无需知道如何创建的细节
 * 3) 工作原理是:通过将一个原型对象传给那个要发动创建的对象，这个要发动创建
 * 的对象通过请求原型对象拷贝它们自己来实施创建，即 对象.clone()
 * 4) 形象的理解：孙大圣拔出猴毛， 变出其它孙大圣
 *
 * todo Spring中原型bean的创建，就是原型模式的应用
 *
 * 1) 对于数据类型是基本数据类型的成员变量，浅拷贝会直接进行值传递，也就是将
 * 该属性值复制一份给新的对象。
 * 2) 对于数据类型是引用数据类型的成员变量，比如说成员变量是某个数组、某个类
 * 的对象等，那么浅拷贝会进行引用传递，也就是只是将该成员变量的引用值（内
 * 存地址）复制一份给新的对象。因为实际上两个对象的该成员变量都指向同一个
 * 实例。在这种情况下，在一个对象中修改该成员变量会影响到另一个对象的该成
 * 员变量值
 * 3) 前面我们克隆羊就是浅拷贝
 * 4) 浅拷贝是使用默认的 clone()方法来实现
 * sheep = (Sheep) super.clone();
 *
 *
 * @author created by barrett in 2020/12/29 06:04
 **/
public class Client {

    public static void main(String[] args) {
        System.out.println("原型模式完成对象的创建");
        // TODO Auto-generated method stub
        Sheep sheep = new Sheep("tom", 1, "白色");

        sheep.friend = new Sheep("jack", 2, "黑色");

        Sheep sheep2 = (Sheep) sheep.clone(); //克隆
        Sheep sheep3 = (Sheep) sheep.clone(); //克隆
        Sheep sheep4 = (Sheep) sheep.clone(); //克隆
        Sheep sheep5 = (Sheep) sheep.clone(); //克隆

        System.out.println("sheep2 =" + sheep2 + "sheep2.friend=" + sheep2.friend.hashCode());
        System.out.println("sheep3 =" + sheep3 + "sheep3.friend=" + sheep3.friend.hashCode());
        System.out.println("sheep4 =" + sheep4 + "sheep4.friend=" + sheep4.friend.hashCode());
        System.out.println("sheep5 =" + sheep5 + "sheep5.friend=" + sheep5.friend.hashCode());
    }

}
