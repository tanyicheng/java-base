package com.barrett.gof.策略模式.Lambda重构优惠券;

import java.math.BigDecimal;
import java.util.function.Function;

public class Client {

    public static void main(String[] args) {
        // 创建购物车实例
        ShoppingCart shoppingCart = new ShoppingCart();
        // 模拟往购物车中加入了总金额为200的商品
        shoppingCart.setAmount(BigDecimal.valueOf(200));
        // 我们要使用的优惠券种类是新客户首单立减10元
        Function<BigDecimal, BigDecimal> discount = (amount) -> (
                BigDecimal.valueOf(10).compareTo(amount) >= 0 ? BigDecimal.ZERO : amount.subtract(BigDecimal.valueOf(10))
        );
        // 设置优惠策略
        shoppingCart.setDiscount(discount);
        // 提交订单
        shoppingCart.submitOrder();
    }
}