package com.barrett.gof.策略模式.优惠券;

import java.math.BigDecimal;

/**
 * 满减优惠，满200减30
 */
public class FullReductionDiscount implements Discount{

    @Override
    public BigDecimal calculate(BigDecimal amount) {
        if(amount.compareTo(BigDecimal.valueOf(200)) >= 0){
            amount = amount.subtract(BigDecimal.valueOf(30));
        }
        return amount;
    }

}