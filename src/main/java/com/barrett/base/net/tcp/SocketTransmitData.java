package com.barrett.base.net.tcp;

import java.io.Serializable;

/**
 * 封装socket数据传输
 * @author created by barrett in 2021/6/25 14:59
 **/
public class SocketTransmitData implements Serializable {
    //读取地址 | 堆栈号
    private String address;
    //读取长度
    private short length;
    //读取的值,或者是需要写入的值
    private String value;
    //数据请求状态
    private boolean success;
    //读或写+数据类型
    private String type;
    //数据包类型
    private String dataType;
    //数据包来源，人工上下料客户端中用作线别
    private String source;
    //数据包目标地
    private String target;
    private Object object;
    //方式，读、写
    @Deprecated
    private String method;


    public SocketTransmitData(boolean success) {
        this.success = success;
    }

    public SocketTransmitData(String type) {
        this.type = type;
    }

    public SocketTransmitData(boolean success, String dataType) {
        this.success = success;
        this.dataType = dataType;
    }

    public SocketTransmitData(String dataType,String address, String type) {
        this.address = address;
        this.type = type;
        this.dataType=dataType;
    }

    public SocketTransmitData(String dataType,String address, String type, String value) {
        this.address = address;
        this.value = value;
        this.type = type;
        this.dataType=dataType;
    }

    public SocketTransmitData(String address, short length, String value, boolean success, String type, String dataType, String source, String target, Object object) {
        this.address = address;
        this.length = length;
        this.value = value;
        this.success = success;
        this.type = type;
        this.dataType = dataType;
        this.source = source;
        this.target = target;
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "SocketTransmitData{" +
                "address='" + address + '\'' +
                ", length=" + length +
                ", value='" + value + '\'' +
                ", success=" + success +
                ", type='" + type + '\'' +
                ", dataType='" + dataType + '\'' +
                ", source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", object=" + object +
                '}';
    }
}
