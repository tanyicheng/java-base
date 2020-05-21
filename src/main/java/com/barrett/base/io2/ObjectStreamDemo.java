package com.barrett.base.io2;

import org.omg.CORBA.OBJ_ADAPTER;

import java.io.*;

/**
 * 对象流：
 * 不是所有的对象都可以 Serializable
 *
 * @Author created by barrett in 2020/5/17 16:40
 */
public class ObjectStreamDemo {

    public static void main(String[] args) throws Exception {
        test2();
    }

    static void test1() throws Exception {
        //写出 序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream dos = new ObjectOutputStream(bos);
        //操作
        dos.writeObject("从入门到放弃");
        dos.writeObject(3);
        //对象
        dos.writeObject(new Employee("马云", 50));


        dos.flush();
        final byte[] bytes = bos.toByteArray();

        //读取 - 反序列化
        ObjectInputStream dis = new ObjectInputStream(new ByteArrayInputStream(bytes));
        //TODO 注意；取数的顺序要一致
        final Object s = dis.readObject();
        if (s instanceof String) {
            System.out.println((String) s);
        }
        final Object v = dis.readObject();
        if (v instanceof Double)
            System.out.println((Double) v);

        Object emp = dis.readObject();
        if (emp instanceof Employee) {
            Employee e = (Employee) emp;
            System.out.println(e.getName() + "  " + e.getAge());
        }

    }

    //序列化到文件
    static void test2() throws Exception {
        //写出 序列化
        ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream("obj.ser"));
        //操作
        dos.writeObject("从入门到放弃");
        dos.writeObject(3);
        //对象
        dos.writeObject(new Employee("马云", 50));

        dos.flush();
        dos.close();

        //读取 - 反序列化
        ObjectInputStream dis = new ObjectInputStream(new FileInputStream("obj.ser"));
        //TODO 注意；取数的顺序要一致
        Object s = dis.readObject();
        Object v = dis.readObject();
        Object emp = dis.readObject();

        if (s instanceof String) {
            System.out.println((String) s);
        }
        if (v instanceof Double) {
            System.out.println((Double) v);
        }

        if (emp instanceof Employee) {
            Employee e = (Employee) emp;
            System.out.println(e.getName() + "  " + e.getAge());
        }
        dis.close();

    }
}

class Employee implements Serializable {
    private transient String name;//todo 此数据不会序列化
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
