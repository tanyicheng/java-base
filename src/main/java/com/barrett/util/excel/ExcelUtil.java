package com.barrett.util.excel;


import java.io.IOException;
import java.util.*;

import static com.barrett.util.excel.ExcelWrite.writeToExcel;

/**
 * @Description //TODO excel 报表数据读取分析生成新的记录
 * @Author barrett
 * @Date 2020/8/11 10:44
 * @Param 
 * @return 
 **/
public class ExcelUtil {
    public static void main(String[] args) throws Exception {
        ExcelRead read = new ExcelRead();

        List<Map<String,Object>> list = new ArrayList<>();
        short column = ExcelRead.getColumn();

        for (int i = 0; i < column; i++) {
            Map<String, Object> result = ExcelRead.readExeclByRowCell(0, i);

            String bl = (String) result.get("bl");
            String container = (String) result.get("container");
            String serialNumber = (String) result.get("serialNumber");
            //内容
            TreeSet<String> data = (TreeSet<String>) result.get("data");
            //属性
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("contract","SS8810C07-TZ");
            dataMap.put("bl",bl);
            dataMap.put("container",container);
            dataMap.put("serialNumber",serialNumber);
            dataMap.put("set",data);

//            new Region();
            //sheet
            Map<String,Object> map = new HashMap();
            map.put("name","sheet"+(i+1)+"");
            map.put("data",dataMap);

            list.add(map);
        }


        writeToExcel(list);
    }

    public static void write(List<Map<String,Object>> list) throws Exception {
        ExcelWrite write = new ExcelWrite();








        for (int i = 0; i < list.size(); i++) {


        }


        writeToExcel(list);
    }
}
