package com.barrett.PLC.conf;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseUnit extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -5691825345029398616L;

    /**
     * 缓存数据集合
     */
    protected static final Map<String, String> cacheDataMap = new HashMap<String, String>();

    protected String id;

    public BaseUnit(String id, Rectangle rectangle) {
        setBorder(new LineBorder(new Color(221, 211, 211)));
        this.setLayout(null);
        this.setBounds(rectangle);
        this.id = id;
        Variables.unitsMap.put(id, this);
        initUI(rectangle);
    }

    /**
     * 
     * 初始化界面
     * 
     * @author shigui.yu
     * @date Aug 11, 2016 9:52:51 AM
     * @param rectangle
     */
    protected abstract void initUI(Rectangle rectangle);

    /**
     * 
     * 缓存数据
     * 
     * @author shigui.yu
     * @date Feb 22, 2017 2:16:37 PM
     * @param key
     * @param value
     */
    public void cacheData(String key, String value) {
        if (value == null) {
            return;
        }
        if (cacheDataMap.containsKey(id + "_" + key) && value.equals(cacheDataMap.get(id + "_" + key))) {
            return;
        }
        cacheDataMap.put(id + "_" + key, value);
    }

    /**
     * 
     * 是否有变化
     * 
     * @author shigui.yu
     * @date Dec 2, 2016 3:33:16 PM
     * @return
     */
    public boolean isChanged(String key, String value) {
        if (cacheDataMap.containsKey(id + "_" + key) && value.equals(cacheDataMap.get(id + "_" + key))) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 保存数据到数据库
     * 
     * @author shigui.yu
     * @date Feb 23, 2017 10:15:18 AM
     * @param sn
     * @param type 1:流水线，2:单机
     * @return
     */
    public boolean saveData(String sn, int type) {
        //        if (!StringUtils.isEmpty(sn)) {
        //TODO 注释掉，提高性能
        //            DataService dataService = Tools.ApplicationContext.getBean(DataService.class);
        //            boolean resultRun = dataService.saveRunData(id, sn, type);
        //            boolean resultHis = dataService.saveHisData(new DataHisModel(id, sn));
        //            return resultRun && resultHis;
        //            return true;
        //        }
        return false;
    }

    /**
     * 
     * 删除数据
     * 
     * @author shigui.yu
     * @date Feb 23, 2017 10:48:37 AM
     * @param type
     * @return
     */
    public boolean delData(int type) {
        //TODO 注释掉，提高性能
        //        DataService dataService = Tools.ApplicationContext.getBean(DataService.class);
        //        return dataService.delRunData(id, type);
        return true;
    }

}
