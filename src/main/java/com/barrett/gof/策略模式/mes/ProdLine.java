package com.barrett.gof.策略模式.mes;
/**
 * 产线刷入
 * 对应策略模式中的 Context（上下文)
 * @author created by barrett in 2021/6/27 21:02
 **/
public class ProdLine {

    //租户
    private MesTenantStrategy tenant;

    //厂别
    private MesFactoryStrategy factory;

    //产线各站点
    private Site site;

    public void brush(){
        tenant.regulation();
        site.commonBrush();
        site.preBrush();
    }

    public MesTenantStrategy getTenant() {
        return tenant;
    }

    public void setTenant(MesTenantStrategy tenant) {
        this.tenant = tenant;
    }

    public MesFactoryStrategy getFactory() {
        return factory;
    }

    public void setFactory(MesFactoryStrategy factory) {
        this.factory = factory;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
