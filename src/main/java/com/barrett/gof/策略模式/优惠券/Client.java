package com.barrett.gof.策略模式.优惠券;

import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) {
      test();

//      test2();
    }

    public static void test(){
        // 创建购物车实例
        ShoppingCart shoppingCart = new ShoppingCart();
        // 模拟往购物车中加入总金额为200的商品
        shoppingCart.setAmount(BigDecimal.valueOf(200));
        // 这里创建具体策略实现类我们简单示意一下，一般开发中是需要前端传过来类型，再通过简单工厂等方式获取具体对象
        // 我们要使用的优惠券种类是新客户首单购买优惠券
        Discount discount = new NewCustomerDiscount();
        //修改使用满减券
        Discount discount2 = new FullReductionDiscount();
        //修改使用折扣券
        Discount discount3 = new PercentageDiscount();

        // 设置优惠策略，todo 先满减，后使用其他券优惠价格最低
        shoppingCart.addDiscount(discount2);
        shoppingCart.addDiscount(discount);
        shoppingCart.addDiscount(discount3);
        // 提交订单
        shoppingCart.submitOrder();
    }

    public static void test2(){
        // 创建购物车实例
        ShoppingCart shoppingCart = new ShoppingCart();
        // 模拟往购物车中加入了总金额为200的商品
        shoppingCart.setAmount(BigDecimal.valueOf(200));
        // 这里创建具体策略实现类我们简单示意一下，一般开发中是需要前端传过来类型，再通过简单工厂等方式获取具体对象
        // 我们要使用的优惠券种类是累积积分类型
        Discount discount = new IntegralDiscount("2020000001");
        // 设置优惠策略
        shoppingCart.addDiscount(discount);
        // 提交订单
        shoppingCart.submitOrder();
    }
}