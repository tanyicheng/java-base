package com.barrett.gof.composite;

/**
 * 抽象类，组合各个单位，适用于树状结构，叶子，节点
 *
 * @author created by barrett in 2021/2/3 22:00
 **/
public abstract class OrganizationComponent {

    private String name; // 名字
    private String des; // 说明

    /**
     * 添加一个单位
     *
     * @author created by barrett in 2021/2/3 22:02
     **/
    protected void add(OrganizationComponent organizationComponent) {
        //默认实现
        throw new UnsupportedOperationException();
    }

    /**
     * 移除一个单位
     *
     * @author created by barrett in 2021/2/3 22:02
     **/
    protected void remove(OrganizationComponent organizationComponent) {
        //默认实现
        throw new UnsupportedOperationException();
    }

    //构造器
    public OrganizationComponent(String name, String des) {
        super();
        this.name = name;
        this.des = des;
    }

    //方法print, 做成抽象的, 子类都需要实现
    protected abstract void print();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }


}
