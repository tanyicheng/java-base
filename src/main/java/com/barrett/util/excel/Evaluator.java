package com.barrett.util.excel;

import java.io.File ;
import java.io.FileInputStream ;
import java.io.FileNotFoundException ;
import java.io.IOException ;
 
import org.apache.poi.openxml4j.exceptions.InvalidFormatException ;
import org.apache.poi.ss.formula.functions.FreeRefFunction ;
import org.apache.poi.ss.formula.udf.AggregatingUDFFinder ;
import org.apache.poi.ss.formula.udf.DefaultUDFFinder ;
import org.apache.poi.ss.formula.udf.UDFFinder ;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference ;

/**
 * https://www.cnblogs.com/ggds/p/7832830.html
 * 自定义函数
 * @Param
 * @return
 * @Author created by barrett in
 */
public class Evaluator {
 
    public static void main( String[] args ) {
 
        System.out.println( "fileName: " + args[0] ) ;
        System.out.println( "cell: " + args[1] ) ;
 
        File workbookFile = new File( args[0] ) ;
 
        try {
            FileInputStream fis = new FileInputStream(workbookFile);
            Workbook workbook = WorkbookFactory.create(fis);
 
            String[] functionNames = { "calculatePayment" } ;
            FreeRefFunction[] functionImpls = { new CalculateMortgage() } ;
 
            UDFFinder udfs = new DefaultUDFFinder( functionNames, functionImpls ) ;
            UDFFinder udfToolpack = new AggregatingUDFFinder( udfs ) ; 
 
            workbook.addToolPack(udfToolpack);
 
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
 
            CellReference cr = new CellReference( args[1] ) ;
            String sheetName = cr.getSheetName() ;
            Sheet sheet = workbook.getSheet( sheetName ) ;
            int rowIdx = cr.getRow() ;
            int colIdx = cr.getCol() ;
            Row row = sheet.getRow( rowIdx ) ;
            Cell cell = row.getCell( colIdx ) ;
 
            CellValue value = evaluator.evaluate( cell ) ;
 
            System.out.println("returns value: " +  value ) ;
 
 
        } catch( FileNotFoundException e ) {
            e.printStackTrace();
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }
}