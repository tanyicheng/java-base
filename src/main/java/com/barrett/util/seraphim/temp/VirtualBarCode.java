package com.barrett.util.seraphim.temp;

import java.io.Serializable;


/**
 * 排版机的虚拟条码，排版机号+焊接机号
 *
 * @author created by barrett in 2021/9/2 16:18
 **/
public class VirtualBarCode implements Serializable {


    public static final String TableName = "mp_virtual_bar_code";


    private String virtualbarcode;


    private String seriesWelding;

    //获取条码时客户端编号


    private String clientNo;
    //排版机号
    private String pbjNo;


    private String batchNo;

}
