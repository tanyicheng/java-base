package com.barrett.util.excel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

/**
 * 读excel
 * TODO Excel 公式小技巧 f9查看，POI设置公式，强制执行，待测试
 */
public class ExcelRead {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";
    private static final String path = "/Users/snipe/Documents/mmkv/temp/500.xlsm";

//    private static final String path = "D:\\Temp\\excel\\old.xlsm";

    public static void main(String[] args) throws Exception {
//        readExecl();
//        Map<String, Object> result = readExeclByRowCell(0, 0);
//
//        result.forEach((key, item) -> {
//            System.out.println(item);
//        });

		getColumn2();
    }



    public static void getColumn2() throws Exception {

        FileInputStream  is = new FileInputStream(path);

        Workbook workbook = getWorkbook(is, XLSX);
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            System.out.println("sheet 数量为0");
            return;
        }
        is.close();
        //遍历该sheet的行
//        Row row = sheet.getRow(0);
//        short lastCellNum = row.getLastCellNum();
//        System.out.println(lastCellNum);
        // 获取第一行数据
        int firstRowNum = sheet.getFirstRowNum();
        Row row1 = sheet.getRow(firstRowNum);
        //列
        Cell cell1 = row1.getCell(1);
        System.out.println(cell1.getNumericCellValue());
//        System.out.println(cell1.getCellFormula());
//        cell1.setCellValue("你好");

        Cell cell2 = row1.createCell(5);
//        cell2.setCellValue("=SUM(A1:A4)");
        cell2.setCellFormula("Addnumber(A1,B1)");
//        cell2.setCellFormula("Code128B(A1)");
        workbook.setForceFormulaRecalculation(true);

        // 首先要创建一个原始Excel文件的输出流对象！
        FileOutputStream excelFileOutPutStream = new FileOutputStream("/Users/snipe/Documents/mmkv/temp/new500.xlsm");
        // 将最新的 Excel 文件写入到文件输出流中，更新文件信息！
        workbook.write(excelFileOutPutStream);
        // 执行 flush 操作， 将缓存区内的信息更新到文件上
        excelFileOutPutStream.flush();
        // 使用后，及时关闭这个输出流对象， 好习惯，再强调一遍！
        excelFileOutPutStream.close();
        workbook.close();
    }

    //获取excel 列
    public static short getColumn() throws Exception {

        InputStream is = new FileInputStream(path);
//        BufferedInputStream is = new BufferedInputStream(new FileInputStream(new File(path)));

//        FileMagic fileMagic = FileMagic.valueOf(is);
//        if (fileMagic.equals(FileMagic.OOXML)) { // XLSX
//            System.out.println("xlsx");
//        } else if (fileMagic.equals(FileMagic.OLE2)) {// XLS
//            System.out.println("xls");
//        }

        Workbook wb = getWorkbook(is, XLSX);
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