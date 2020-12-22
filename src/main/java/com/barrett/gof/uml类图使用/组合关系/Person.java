package com.barrett.gof.uml类图使用.组合关系;

/**
 * 组合关系：也是整体与部分的关系，但是整体与部分不可以分开。 再看一个案例：在程序中我们定义实体：Person与IDCard、Head, 那么 Head 和
 * Person 就是 组合，IDCard 和 Person 就是聚合。
 * 但是如果在程序中Person实体中定义了对IDCard进行级联删除，即删除Person时
 * 连同IDCard一起删除，那么IDCard 和 Person 就是组合了
 * @author created by barrett in 2020/12/22 09:07
 **/
public class Person {
    private IDCard card; //聚合关系
    private Head head = new Head(); //组合关系

}
