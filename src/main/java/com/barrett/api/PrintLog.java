package com.barrett.api;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 打印日志实体
 * @author fishcat
 * @date 2019-01-02 14:06
 */
public class PrintLog {

    /**
     * 主键
     */
    private String mid;

    /**
     * 客户端编号
     */
    private String clientNo;

    /**
     * 打印条码
     */
    private String barcode;

    /**
     * 条码批次
     */
    private String batchNo;

    /**
     * 生产线
     */
    private String line;

    /**
     * 功率
     */
    private String pmax;

    /**
     * 功率档
     */
    private String pmaxGear;

    /**
     * 电流
     */
    private String imax;

    /**
     * 电流档
     */
    private String imaxGear;

    /**
     * 打印类型(铭牌，条码)，参考NameplateConstants
     */
    private String type;

    /**
     * 打印方式(自动，手动)，参考NameplateConstants
     */
    private String auto;

    /**
     * 打印参数
     */
    private String params;

    /**
     * 创建时间
     */
    private String createTime;

    public PrintLog() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.createTime = dateFormat.format(new Date());
    }



    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getPmax() {
        return pmax;
    }

    public void setPmax(String pmax) {
        this.pmax = pmax;
    }

    public String getPmaxGear() {
        return pmaxGear;
    }

    public void setPmaxGear(String pmaxGear) {
        this.pmaxGear = pmaxGear;
    }

    public String getImax() {
        return imax;
    }

    public void setImax(String imax) {
        this.imax = imax;
    }

    public String getImaxGear() {
        return imaxGear;
    }

    public void setImaxGear(String imaxGear) {
        this.imaxGear = imaxGear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PrintLog{" + "mid='" + mid + '\'' +
                ", clientNo='" + clientNo + '\'' +
                ", barcode='" + barcode + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", line='" + line + '\'' +
                ", pmax='" + pmax + '\'' +
                ", pmaxGear='" + pmaxGear + '\'' +
                ", imax='" + imax + '\'' +
                ", imaxGear='" + imaxGear + '\'' +
                ", type='" + type + '\'' +
                ", auto='" + auto + '\'' +
                ", params='" + params + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
