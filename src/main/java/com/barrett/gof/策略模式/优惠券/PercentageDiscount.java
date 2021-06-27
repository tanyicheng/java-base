package com.barrett.gof.策略模式.优惠券;

import java.math.BigDecimal;

/**
 * 折扣券优惠，所有商品打9折
 * 新增 PercentageDiscount （折扣券优惠类）
 */
public class PercentageDiscount implements Discount{

    @Override
    public BigDecimal calculate(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(0.9));
    }
}