package com.barrett.gof.策略模式.优惠券;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车
 * 对应策略模式中的 Context（上下文)
 */
public class ShoppingCart {

    /**
     * 优惠折扣计算类
     * 这里先演示只能选择一种优惠类型情况，
     * 如果想要支持多种优惠同时使用，可以定义为List<Discount>
     */
    private List<Discount> discountList;

    /**
     * 购物车中商品的总金额
     */
    private BigDecimal amount;

    /**
     * 提交订单主流程
     */
    public void submitOrder() {

        // 计算优惠减免
        if (discountList != null && discountList.size() != 0) {
            System.out.println("优惠前总金额：" + amount);
            for (Discount discount : discountList) {
                amount = discount.calculate(amount);
                System.out.println("每次优惠后总金额：" + amount);
            }
        }


        // 保存订单并付款

        // 送货到家

    }

    /**
     * 设置具体的优惠策略
     *
     * @param discount 优惠策略对象
     */
    public void addDiscount(Discount discount) {
        if (discountList == null) {
            discountList = new ArrayList<>();
        }
        this.discountList.add(discount);
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