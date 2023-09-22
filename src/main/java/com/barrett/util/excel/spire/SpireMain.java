package com.barrett.util.excel.spire;

import com.spire.xls.ExcelVersion;
import com.spire.xls.FileFormat;
import com.spire.xls.Workbook;

public class SpireMain {

    public static void main(String[] args) {
        //创建一个Workbook实例并加载Excel文件
        Workbook workbook = new Workbook();
        workbook.loadFromFile("/Users/snipe/Documents/temp/800.xlsm");

        //设置转换后的PDF页面高宽适应工作表的内容大小
        workbook.getConverterSetting().setSheetFitToPage(true);

        //将生成的文档保存到指定路径
        workbook.saveToFile("/Users/snipe/Documents/temp/output/ExcelToPdf.pdf", FileFormat.PDF);
    }
}
