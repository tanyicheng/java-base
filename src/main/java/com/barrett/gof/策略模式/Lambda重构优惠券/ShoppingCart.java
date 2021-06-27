package com.barrett.gof.策略模式.Lambda重构优惠券;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * 购物车
 */
public class ShoppingCart {

    /**
     * 优惠折扣计算类，输入一个BigDecimal类型值，返回一个BigDecimal类型值
     * 这里先演示只能选择一种优惠类型情况
     * 如果想要支持多种优惠同时使用，可以定义为LIst<Function<BigDecimal,BigDecimal>>
     */
    private Function<BigDecimal, BigDecimal> discount;

    /**
     * 购物车中商品的总金额
     */
    private BigDecimal amount;


    /**
     * 提交订单主流程
     */
    public void submitOrder() {

        // 计算优惠减免
        if (discount != null) {
            System.out.println("优惠前总金额：" + amount);
            amount = discount.apply(amount);
            System.out.println("优惠后总金额：" + amount);
        }


        // 保存订单并付款

        // 送货到家

    }

    /**
     * 设置具体的优惠策略
     *
     * @param discount 优惠策略对象
     */
    public void setDiscount(Function<BigDecimal, BigDecimal> discount) {
        this.discount = discount;
    }

    /**
     * 模拟输入购物车中物品金额
     *
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}