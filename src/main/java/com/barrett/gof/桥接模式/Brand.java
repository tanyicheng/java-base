package com.barrett.gof.桥接模式;

/**
 * 接口类  品牌
 * 每个品牌下的手机功能不同
 *
 * @author created by barrett in 2021/1/25 19:59
 **/
public interface Brand {
    /**
     * 打开
     * @author created by barrett in 2021/1/25 19:59
     **/
    void open();

    /**
     * 关闭
     * @author created by barrett in 2021/1/25 20:00
     **/
    void close();

    /**
     * 打电话
     * @author created by barrett in 2021/1/25 20:26
     **/
    void call();
}
