package com.barrett.util.PLC.conf;


public class PlcConfigs {

    public static long plcReadInterval = 500;

    public static int portData = 4001;
    public static int portTimer = 4002;
    public static int portCheck = 4003;

    public static String ipBcj6_1 = "192.168.1.20";
    public static String ipBcj6_2 = "192.168.1.21";
    public static String ipBcj6_3 = "192.168.1.22";

    public static String ipCqj6_1 = "192.168.1.40";
    public static String ipCqj6_2 = "192.168.1.41";
    public static String ipCqj6_3 = "192.168.1.42";
    public static String ipCqj6_4 = "192.168.1.43";

    public static String ipLsx6_1 = "192.168.1.60";
    public static String ipLsx6_2 = "192.168.1.61";
    public static String ipLsx6_3 = "192.168.1.62";

    public static String ipBcj7_1 = "192.168.1.10";
    public static String ipBcj7_2 = "192.168.1.11";
    public static String ipBcj7_3 = "192.168.1.12";

    public static String ipCqj7_1 = "192.168.1.30";
    public static String ipCqj7_2 = "192.168.1.31";
    public static String ipCqj7_3 = "192.168.1.32";
    public static String ipCqj7_4 = "192.168.1.33";

    public static String ipLsx7_1 = "192.168.1.50";
    public static String ipLsx7_2 = "192.168.1.51";
    public static String ipLsx7_3 = "192.168.1.52";

    static {
//        Tools.Configs.loadProperties("configs/plc.properties", PlcConfigs.class);
    }

}
