package com.barrett.base.bigDecimal;

import java.math.BigDecimal;

public class BigDecimalDemo {

    public static void main(String[] args) {
        divide();
    }

    public static void divide(){

        int a = 10;
        int b=11;
        BigDecimal aa = new BigDecimal(a);
        BigDecimal bb = new BigDecimal(b);

        BigDecimal divide = aa.divide(bb, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);
    }
}
