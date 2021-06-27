package com.barrett.gof.策略模式.mes;

/**
 * 赛拉弗的租户
 * @author created by barrett in 2021/6/27 21:11
 **/
public class SeraphimMes implements MesTenantStrategy {

    @Override
    public String regulation() {
        System.out.println("seraphim 租户的刷入规则");
        return null;
    }
}
