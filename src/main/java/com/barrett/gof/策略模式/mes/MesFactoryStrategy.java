package com.barrett.gof.策略模式.mes;

/**
 * 根据厂别区分刷入规则
 * 对应模式中的 Strategy （策略接口）
 * @author created by barrett in 2021/6/27 20:50
 **/
public interface MesFactoryStrategy {

    //各租户的刷入规则
    String regulation();

}
