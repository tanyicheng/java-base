package com.barrett.gof.组合模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 再扩展一个子节点，假设这是在系和学院之间的节点
 * @author created by barrett in 2021/2/3 22:10
 **/
public class Child extends OrganizationComponent {

    //存放系下面的子级
    List<OrganizationComponent> oc = new ArrayList<>();

    public Child(String name, String des) {
        super(name, des);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        oc.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        oc.remove(organizationComponent);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    protected void print() {
        System.out.println("--------------"+getName()+"--------------");
        for (OrganizationComponent component : oc) {
            component.print();

        }
    }
}
