package com.barrett.util.seraphim.temp;


/**
 * plc设备传递过来的数据接收类，
 * @author created by barrett in 2021/7/1 11:02
 **/
public class ProdLineTransmitByCient {
    public static final String TableName = "plc_collect_prodline";
    //读取地址
    private String address;
    //读取的值,或者是需要写入的值
    private String barcode;
    //工位
    private String station;
    private String desc;
    private String ip;
    private String line;
    private String factory;

}
