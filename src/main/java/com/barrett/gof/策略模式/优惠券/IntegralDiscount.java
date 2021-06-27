package com.barrett.gof.策略模式.优惠券;

import java.math.BigDecimal;

/**
 * 如果我们又需要扩展一种较特殊的优惠策略，我们不在金额上做减免，而是给客户的账号里累积积分，客户可以通过积分换购商品
 * <p>
 * 购买商品，可以累积同金额的积分，客户可以通过积分换购商品
 */
public class IntegralDiscount implements Discount {

    public IntegralDiscount(String account) {
        this.account = account;
    }

    /**
     * 客户账号
     */
    private String account;

    @Override
    public BigDecimal calculate(BigDecimal amount) {
        System.out.println(String.format("给账号[%s]中加[%d]积分", account, amount.intValue()));
        return amount;
    }
}