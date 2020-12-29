package com.barrett.gof.原型模式.深拷贝;

/**
 * 深拷贝
 * 1) 复制对象的所有基本数据类型的成员变量值
 * 2) 为所有引用数据类型的成员变量申请存储空间，并复制每个引用数据类型成员变
 * 量所引用的对象，直到该对象可达的所有对象。也就是说，对象进行深拷贝要对
 * 整个对象进行拷贝
 * 3) 深拷贝实现方式1：重写clone方法来实现深拷贝
 * 4) 深拷贝实现方式2：通过对象序列化实现深拷贝(推荐)
 *
 * @author created by barrett in 2020/12/29 06:19
 **/
public class Client {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        DeepProtoType p = new DeepProtoType();
        p.name = "张三" ;
        p.deepCloneableTarget = new DeepCloneableTarget("大牛", "小牛");

        //方式1 完成深拷贝

//        DeepProtoType p2 = (DeepProtoType) p.clone();

        //方式2 完成深拷贝
		DeepProtoType p2 = (DeepProtoType) p.deepClone();

        System.out.println("p.name=" + p.name + " hashcode=" + p.hashCode() + " p.deepCloneableTarget=" + p.deepCloneableTarget.hashCode());
        System.out.println("p2.name=" + p2.name + " hashcode=" + p2.hashCode() + " p2.deepCloneableTarget=" + p2.deepCloneableTarget.hashCode());

    }

}
