package com.barrett.util.PLC.conf;


public class CenterConfigs {

    /**
     * 厂别
     */
    public static String factory = "4";

    /**
     * 线别
     */
    public static String LINE_6 = "006";
    public static String LINE_7 = "007";

    /**
     * 排版机号
     */
    public static String pbjNos_6 = "1;2;3";
    public static String pbjNos_7 = "1;2;3";

    /**
     * 焊接机号
     */
    public static String hjjNos_6 = "1,2;3,4;5,6";
    public static String hjjNos_7 = "1,2;3,4;5,6";

    /**
     * 子客户端连接监听端口
     */
    public static int listenPortHandSn = 15000;
    public static int listenPortCdPrint = 15001;
    public static int listenPortExportSn = 15002;
    public static int listenPortImportSn = 15003;
    public static int listenPortRefreshData = 15004;
    public static int listenELSn = 15005;
    public static int listenGlSn = 15006;
    public static int listenPortMulticast = 15555;
    public static int modbus = 502;

    static {
//        Tools.Configs.loadProperties("configs/center.properties", CenterConfigs.class);
    }

    /**
     * 
     * 获取排版机号
     * 
     * @author shigui.yu
     * @date Feb 23, 2017 5:23:59 PM
     * @param index
     * @param lineNo
     * @return
     */
    public static String getPbjNo(int index, String lineNo) {
        if (LINE_6.equals(lineNo)) {
            String[] arr = pbjNos_6.split(";");
            if (index < arr.length) {
                return arr[index];
            }
        } else if (LINE_7.equals(lineNo)) {
            String[] arr = pbjNos_7.split(";");
            if (index < arr.length) {
                return arr[index];
            }
        }
        return String.valueOf(index);
    }

    /**
     * 
     * 获取焊接机号
     * 
     * @author shigui.yu
     * @date Feb 23, 2017 5:08:49 PM
     * @param index
     * @param pbjNo
     * @return
     */
    public static String getHjjNo(int index, String pbjNo, String lineNo) {
        if (LINE_6.equals(lineNo)) {
            String[] pbjArr = pbjNos_6.split(";");
            for (int i = 0; i < pbjArr.length; i++) {
                if (pbjArr[i].equals(pbjNo)) {
                    return hjjNos_6.split(";")[i].split(",")[index];
                }
            }
        } else if (LINE_7.equals(lineNo)) {
            String[] pbjArr = pbjNos_7.split(";");
            for (int i = 0; i < pbjArr.length; i++) {
                if (pbjArr[i].equals(pbjNo)) {
                    return hjjNos_7.split(";")[i].split(",")[index];
                }
            }
        }
        return String.valueOf(index);
    }

}
