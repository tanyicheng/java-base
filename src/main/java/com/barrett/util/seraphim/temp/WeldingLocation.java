package com.barrett.util.seraphim.temp;

/**
 * 串焊机plc数据地址
 * @author created by barrett in 2020/12/3 21:36
 **/
public class WeldingLocation {
    public static final String TableName = "ln_welding_collect";

    //当前产量A侧
    private String currentYieldA;
    //当前产量B侧
    private String currentYieldB;
    //当前总产量
    private String currentYieldTotal;
    //坏片数量A侧
    private String badNumberA;
    //坏片数量B侧
    private String badNumberB;
    //总坏片数
    private  String badNumberTotal;
    //NG串A侧
    private String ngStringA;
    //NG串B侧
    private String ngStringB;
    //坏串总数量
    private  String badStringTotal;
    //串号
    private String stringCode;

    //设备状态A侧 1运行中，2待机，3故障
    private String equipmentStatusA;
    //设备状态B侧
    private String equipmentStatusB;

    //产量清零信号 1清零，0无操作  plc显示2秒
    private String yieldCleanSignal;
    //A侧取NG片信号 1取NG片，0无操作
    private String ngPieceSignalA;
    //B侧取NG片信号
    private String ngPieceSignalB;
    //取NG串信号A侧 1取NG串，0无操作
    private String ngStringSignalA;
    //取NG串信号B侧
    private String ngStringSignalB;
    //串判定自动/手动 1人工，0自动
    private String stringJudge;

    //焊接温度 那片划分 D6200 - D6211
    private String weldTemperature1;
    private String weldTemperature2;
    private String weldTemperature3;
    private String weldTemperature4;
    private String weldTemperature5;
    private String weldTemperature6;
    private String weldTemperature7;
    private String weldTemperature8;
    private String weldTemperature9;
    private String weldTemperature10;
    private String weldTemperature11;
    private String weldTemperature12;
}
