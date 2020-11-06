package com.barrett.util.PLC.conf;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Variables {

    /**
     * 标题常量
     */
    public static final String FRAME_TITLE_DATA_UI = "实时数据界面";

    /**
     * 产线单元前缀
     */
    public static final String UNIT_ID_PREFIX_LINE6 = "6_";
    public static final String UNIT_ID_PREFIX_LINE7 = "7_";

    /********************** 分割线 ***********************/

    /**
     * 线程池
     */
    public final static ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * 产线单元集合
     */
    public static final Map<String, BaseUnit> unitsMap = new HashMap<String, BaseUnit>();


}
