package com.barrett.gof.策略模式.优惠券;

import java.math.BigDecimal;

/**
 * 优惠折扣计算接口
 * 对应模式中的 Strategy （策略接口）
 */
public interface Discount {

    /**
     * 计算优惠后的金额
     * @param amount
     * @return 优惠后的金额
     */
    BigDecimal calculate(BigDecimal amount);

}