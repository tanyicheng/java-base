package com.barrett.gof.策略模式.mes.site;

import com.barrett.gof.策略模式.mes.MesTenantStrategy;
import com.barrett.gof.策略模式.mes.Site;

/**
 * 层叠站
 * @author created by barrett in 2021/6/27 20:51
 **/
public class StackUp extends Site implements MesTenantStrategy {

    @Override
    public String regulation() {
        return null;
    }

    @Override
    public void preBrush() {
        System.out.println("层叠站刷入前，规则校验");
    }
}
