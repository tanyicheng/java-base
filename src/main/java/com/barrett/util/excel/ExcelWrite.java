package com.barrett.util.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CreationHelper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

public class ExcelWrite {
    public static void main(String[] args) throws Exception {

        writeToExcel(null);
    }

    public static void writeToExcel(List<Map<String, Object>> list) throws Exception {
        //创建一个工作簿 即excel文件,再在该文件中创建一个sheet
        HSSFWorkbook wb = new HSSFWorkbook();

        //遍历sheet页
        for (Map<String, Object> mapList : list) {
            String name = (String) mapList.get("name");
            Map<String, Object> data = (Map<String, Object>) mapList.get("data");

            HSSFSheet sheet = wb.createSheet(name);
            String contract = (String) data.get("contract");
            String bl = (String) data.get("bl");
            String container = (String) data.get("container");
            String serialNumber = (String) data.get("serialNumber");
            TreeSet<String> set = (TreeSet<String>) data.get("set");
            List<String> dataList = new ArrayList<>(set);


            HSSFRow row = sheet.createRow(1);
            row.createCell(0).setCellValue("Seraphim Delivery Details for Braux under Contract No. " + contract);

            row = sheet.createRow(2);
            row.createCell(0).setCellValue("BL Number:  " + bl);
            row.createCell(5).setCellValue("Container Number:  " + container);

            row = sheet.createRow(4);
            row.createCell(0).setCellValue("Serial Number: " + serialNumber + "+below numbers");


            int num = 0;
            //第6行开始，下标从0开始
            for (int i = 5; i < dataList.size() + 5; i++) {
                //在sheet中创建一行
                row = sheet.createRow(i);
                //列处理
                for (int j = 0; j < 10; j++) {
                    if(num == dataList.size())
                        break;

                    row.createCell(j).setCellValue(dataList.get(num));
                    num++;
                }

            }
        }


        //最后写回磁盘
        FileOutputStream out = new FileOutputStream("D:\\Temp\\SerialNumbersEnd.xlsx");
        wb.write(out);
        out.close();

        System.out.println("写完了!");
    }


    /**
     * @return
     * @Description //TODO 基础的写入demo
     * @Author barrett
     * @Date 2020/8/10 15:35
     * @Param
     **/
    public static void baseWrite() throws Exception {
        //创建一个工作簿 即excel文件,再在该文件中创建一个sheet
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("第一个sheet");

        //在sheet中创建一行
        HSSFRow row = sheet.createRow(0);

        //在该行写入各种类型的数据
        row.createCell(0).setCellValue(true);
        row.createCell(1).setCellValue("钟林森");
        row.createCell(2).setCellValue(23);

        //设置保留两位小数
        HSSFCell cell = row.createCell(3);
        cell.setCellValue(6000);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        cell.setCellStyle(cellStyle);

        //在写入 日期格式的 数据需要进行特殊处理(这是一种 简单的处理方式)
        CreationHelper createHelper = wb.getCreationHelper();
        HSSFCellStyle style = wb.createCellStyle();
        style.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        cell = row.createCell(4);
        cell.setCellValue(new Date());
        cell.setCellStyle(style);

        //最后写回磁盘
        FileOutputStream out = new FileOutputStream("D:\\Temp\\SerialNumbersEnd.xlsx");
        wb.write(out);
        out.close();

        System.out.println("写完了!");
    }
}