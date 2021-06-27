package com.barrett.gof.策略模式.优惠券;

import java.math.BigDecimal;

/**
 * 新客户优惠，首单立减10元
 * NewCustomerDiscount（新客户优惠计算类）、FullReductionDiscount（满减券优惠计算类），对应模式中的 ConcreteStratery（策略具体实现类
 */
public class NewCustomerDiscount implements Discount{

    @Override
    public BigDecimal calculate(BigDecimal amount) {
        if(BigDecimal.valueOf(10).compareTo(amount) >= 0){
            return BigDecimal.ZERO;
        }
        return amount.subtract(BigDecimal.valueOf(10));
    }

}