package com.barrett.beans;

import java.io.Serializable;

public class Person implements Serializable {
    private Integer id;
    private String name;
    private String age;
    private String address;
    private String iphone;
    private Double num;

    public Person(Integer id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public Person(Integer id, String name, String age, String address, String iphone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.iphone = iphone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", iphone='" + iphone + '\'' +
                ", num=" + num +
                '}';
    }
}
