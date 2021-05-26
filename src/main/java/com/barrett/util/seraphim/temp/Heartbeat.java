package com.barrett.util.seraphim.temp;


/**
 * IV,EL设备等心跳检测
 *
 * @author created by barrett in 2021/5/24 10:22
 **/
public class Heartbeat {
    public static final String TableName = "devices_heartbeat";

    //设备编号
    private String deviceNo;
    //厂别
    private String factory;
    //线别
    private String line;
    //备注
    private String remark;
    private String staffId;


}
