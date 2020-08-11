package com.barrett.util.excel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

/**
 * 读excel
 */
public class ExcelRead {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";
    private static final String path = "D:\\Temp\\Serial Numbers.xlsx";


    public static void main(String[] args) throws Exception {
//        readExecl();
//        Map<String, Object> result = readExeclByRowCell(0, 0);
//
//        result.forEach((key, item) -> {
//            System.out.println(item);
//        });

		getColumn();
    }

    //获取excel 列
    public static short getColumn() throws Exception {

        InputStream stream = new FileInputStream(path);

        Workbook wb = getWorkbook(stream, XLSX);
        Sheet sheet = wb.getSheetAt(0);
        if (sheet == null) {
            return 0;
        }

        //遍历该sheet的行
        Row row = sheet.getRow(0);
		short lastCellNum = row.getLastCellNum();
		System.out.println(lastCellNum);
		return lastCellNum;
	}

    //指定位置读取 excel
    public static Map<String, Object> readExeclByRowCell(int rowPosition, int cellPosition) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();
        TreeSet<String> treeSet = new TreeSet<>();
        String bl = "";
        String container = "";
        String serialNumber = "";

        //读取一个excel表的内容
        InputStream stream = new FileInputStream(path);

        Workbook wb = getWorkbook(stream, XLSX);

        //获取excel表的第一个sheet
        Sheet sheet = wb.getSheetAt(0);
        if (sheet == null) {
            return null;
        }

        //遍历该sheet的行
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                continue;
            }

            //再遍历改行的所有列  row.getLastCellNum()
            for (int cellNum = cellPosition; cellNum <= cellPosition; cellNum++) {
                Cell cell = row.getCell(cellNum);
                if (cell == null) {
                    continue;
                }

                String strVal = readCellSecondMethod(cell);
                if (StringUtils.isEmpty(strVal)) {
                    continue;
                }
                if (rowNum == 1) {
                    bl = strVal;
                }
                if (rowNum == 3) {
                    container = strVal;
                }
                String serialEnding = strVal;
                //第6行开始，下标从0开始的
                if (rowNum >= 5) {
                    if (StringUtils.isEmpty(serialNumber))
                        serialNumber = strVal.substring(0, strVal.length() - 4);

                    //截取后四位
                    serialEnding = strVal.substring(strVal.length() - 4, strVal.length());
                    treeSet.add(serialEnding);
                }

//				System.out.print(" " + serialEnding);

            }
//			System.out.println();
        }
        stream.close();

        resultMap.put("bl", bl);
        resultMap.put("container", container);
        resultMap.put("serialNumber", serialNumber);
        resultMap.put("data", treeSet);

        return resultMap;
    }


    //正常读取 excel
    public static void readExecl() throws IOException {
        //读取一个excel表的内容
        InputStream stream = new FileInputStream("D:\\Temp\\Serial Numbers.xlsx");

        Workbook wb = getWorkbook(stream, XLSX);

        //获取excel表的第一个sheet
        Sheet sheet = wb.getSheetAt(0);
        if (sheet == null) {
            return;
        }

        //遍历该sheet的行
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                continue;
            }

            //再遍历改行的所有列
            for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
                Cell cell = row.getCell(cellNum);
                if (cell == null) {
                    continue;
                }

                String strVal = readCellSecondMethod(cell);
                //System.out.print(" " + readCellFirstMethod(cell));
//                if (cellNum == 2) {
//                    strVal = strVal.contains(".") ? strVal.substring(0, strVal.indexOf(".")) : strVal;
//                }
                System.out.print(" " + strVal);


            }
            System.out.println();
        }

        stream.close();
    }


    /**
     * 第一种方法
     * 读取excel单元格的内容并针对其type进行不同的处理,
     * 其中就包含  读取excel表格中日期格式的cell
     *
     * @param cell
     * @return
     */
    public static String readCellFirstMethod(Cell cell) {

        if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
            }
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return cell.getStringCellValue();
        }
    }

    /**
     * 第二种方法
     * 读取excel单元格的内容并针对其type进行不同的处理,
     * 其中就包含  读取excel表格中日期格式的cell
     *
     * @param cell
     * @return
     */
    public static String readCellSecondMethod(Cell cell) {
        //DecimalFormat df = new DecimalFormat("#");
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {

            //数字
            case NUMERIC:

                //日期格式的处理
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                }

                return String.valueOf(cell.getNumericCellValue());
            //return df.format(cell.getNumericCellValue());

            //字符串
            case STRING:
                return cell.getStringCellValue();

            //公式
            case FORMULA:
                return cell.getCellFormula();

            //空白
            case BLANK:
                return "";

            //布尔取值
            case BOOLEAN:
                return cell.getBooleanCellValue() + "";

            //错误类型
            case ERROR:
                return cell.getErrorCellValue() + "";
        }

        return "";
    }

    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

}