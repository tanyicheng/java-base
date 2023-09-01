package com.barrett.test;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.barcode.PdfCodabarBarcode;
import com.spire.pdf.barcode.PdfCode128ABarcode;
import com.spire.pdf.barcode.PdfCode39Barcode;
import com.spire.pdf.barcode.TextLocation;
import com.spire.pdf.general.find.PdfTextFind;
import com.spire.pdf.general.find.PdfTextFindCollection;
import com.spire.pdf.graphics.*;
import com.spire.xls.*;
import org.junit.Test;

import javax.print.PrintService;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.EnumSet;

import static com.spire.pdf.graphics.PdfFontStyle.Bold;

public class SpireExcelTest {

    String excelPath = "/Users/snipe/Documents/temp/exporttemp_img.xls";

    //替换文字
    @Test
    public void replace() {

        //创建Workbook实例
        Workbook workbook = new Workbook();
        //加载Excel文档
        workbook.loadFromFile("/Users/snipe/Documents/temp/工作簿1.xlsx");

        //获取第一个工作表
        Worksheet worksheet = workbook.getWorksheets().get(0);

        //查找工作表中的指定文字
        CellRange[] ranges = worksheet.findAllString("${name}", true, true);
        CellRange oldText = worksheet.findString("${name}", true, true);
        oldText.setText("300万");


        for (CellRange range : ranges) {
            //替换为新文字
            range.setText("替换");
        }

        //保存结果文档
        workbook.saveToFile("/Users/snipe/Documents/temp/工作簿1.xlsx", ExcelVersion.Version2013);
    }

    @Test
    public void excelToHTML() {
        //加载Excel工作表
        Workbook wb = new Workbook();
        wb.loadFromFile(excelPath);

        //获取工作表
        Worksheet sheet = wb.getWorksheets().get(0);

        //添加图片到工作表的指定位置
        ExcelPicture pic = sheet.getPictures().add(3, 3, "/Users/snipe/Documents/temp/output/barcode.png");
        //设置图片的宽度和高度
//        pic.setWidth(500);
//        pic.setHeight(300);

        //调用方法将Excel工作表保存为图片
//        sheet.saveToImage("/Users/snipe/Documents/temp/output/ToImg.png");
        //调用方法，将指定Excel单元格数据范围保存为图片
        //sheet.saveToImage("ToImg2.png",8,1,30,7);

        //调用方法将Excel保存为HTML
        sheet.saveToHtml("/Users/snipe/Documents/temp/output/ToHtml.html");

//        //调用方法将Excel保存为XPS
//        sheet.saveToFile("/Users/snipe/Documents/temp/output/ToXPS.xps", String.valueOf(FileFormat.XPS));
//
//        //调用方法将Excel保存为CSV
//        sheet.saveToFile("/Users/snipe/Documents/temp/output/ToCSV.csv", String.valueOf(FileFormat.CSV));
//
//        //调用方法将Excel保存为XML
//        sheet.saveToFile("/Users/snipe/Documents/temp/output/ToXML.xml", String.valueOf(FileFormat.XML));
//
//        //调用方法将Excel保存为PostScript
//        sheet.saveToFile("/Users/snipe/Documents/temp/output/ToPostScript.postscript", String.valueOf(FileFormat.PostScript));
//
//        //调用方法将Excel保存为PCL
//        sheet.saveToFile("/Users/snipe/Documents/temp/output/ToPCL.pcl", String.valueOf(FileFormat.PCL));

    }

    @Test
    public void excelToPDF() {
        //创建一个Workbook实例并加载Excel文件
        Workbook workbook = new Workbook();
        workbook.loadFromFile("/Users/snipe/Documents/temp/工作簿1.xlsx");

        //获取工作表
        Worksheet sheet = workbook.getWorksheets().get(0);

        ExcelPicture pic1 = sheet.getPictures().add(7, 2, "/Users/snipe/Documents/temp/output/barcodes/BAR2023-06-26-0.png");
        pic1.setTopRowOffset(50);
        pic1.setLeftColumnOffset(200);
        ExcelPicture pic2 = sheet.getPictures().add(7, 6, "/Users/snipe/Documents/temp/output/barcodes/BAR2023-06-26-1.png");
        pic2.setTopRowOffset(50);
        pic2.setLeftColumnOffset(50);

        //设置转换后的PDF页面高宽适应工作表的内容大小
        workbook.getConverterSetting().setSheetFitToPage(true);

        //将生成的文档保存到指定路径
        workbook.saveToFile("/Users/snipe/Documents/temp/output/ExcelToPdf3.pdf", FileFormat.PDF);
    }

    @Test
    public void pdfAddImg() {
        //创建 PdfDocument 类的对象
        PdfDocument doc = new PdfDocument();

        //载入PDF文档
        doc.loadFromFile("/Users/snipe/Documents/temp/output/ExcelToPdf2.pdf");

        //获取文档的第一页
        PdfPageBase page = doc.getPages().get(0);

        //搜索文本”心理治疗师“
        PdfTextFindCollection collection = page.findText("技术部", false);

        //指定替换文本”心理医生“
        String newText = "治疗师";

        //创建 PdfTrueTypeFont 类的对象以设置字体
        PdfTrueTypeFont font = new PdfTrueTypeFont(new Font("华文中宋", Font.BOLD, 12));

        for (Object findObj : collection.getFinds()) {
            PdfTextFind find = (PdfTextFind) findObj;

            //获取文本在页面中的范围
            Rectangle2D.Float rec = (Rectangle2D.Float) find.getBounds();
            page.getCanvas().drawRectangle(PdfBrushes.getWhite(), rec);

            //绘制文本
            page.getCanvas().drawString(newText, font, PdfBrushes.getBlue(), rec.getX(), rec.getY() - 3);
        }

        String result = "/Users/snipe/Documents/temp/output/替换文本.pdf";

        //保存文档
        doc.saveToFile(result);
    }

    @Test
    public void pdfAddBarcode() {
        //创建PdfDocument对象
        PdfDocument doc = new PdfDocument();

        //添加一页
        PdfPageBase page = doc.getPages().add();

        //初始化y变量
        double y = 15;

        //创建字体
        PdfFont font = new PdfFont(PdfFontFamily.Helvetica, 12, EnumSet.of(Bold));

        // 绘制文本“Codebar:”到PDF
        PdfTextWidget text = new PdfTextWidget();
        text.setFont(font);
        text.setText("Codebar:");
        PdfLayoutResult result = text.draw(page, 0, y);
        y = (float) (result.getBounds().getY() + result.getBounds().getHeight() + 2);

        //绘制Codebar条码到PDF
        PdfCodabarBarcode codebar = new PdfCodabarBarcode("00:12-3456/7890");
        codebar.setBarcodeToTextGapHeight(1f);
        codebar.setBarHeight(50f);
        codebar.setEnableCheckDigit(true);
        codebar.setShowCheckDigit(true);
        codebar.setTextDisplayLocation(TextLocation.Bottom);
        PdfRGBColor blue = new PdfRGBColor(Color.blue);
        codebar.setTextColor(blue);
        Point2D.Float point = new Point2D.Float();
        point.setLocation(0, y);
        codebar.draw(page, point);
        y = codebar.getBounds().getY() + codebar.getBounds().getHeight() + 5;

        //绘制文本“Code128-A:”到PDF
        text.setText("Code128-A:");
        result = text.draw(page, 0, y);
        page = result.getPage();
        y = result.getBounds().getY() + result.getBounds().getHeight() + 2;

        //绘制Code128A条码到PDF
        PdfCode128ABarcode code128 = new PdfCode128ABarcode("HELLO 00-123");
        code128.setBarcodeToTextGapHeight(1f);
        code128.setBarHeight(50f);
        code128.setTextDisplayLocation(TextLocation.Bottom);
        code128.setTextColor(blue);
        point.setLocation(point.x, y);
        code128.draw(page, point);
        y = code128.getBounds().getY() + code128.getBounds().getHeight() + 5;

        //绘制文本“Code39”到PDF
        text.setText("Code39:");
        result = text.draw(page, 0, y);
        page = result.getPage();
        y = result.getBounds().getY() + result.getBounds().getHeight() + 2;

        //绘制Code39条形码到PDF
        PdfCode39Barcode code39 = new PdfCode39Barcode("16-273849");
        code39.setBarcodeToTextGapHeight(1f);
        code39.setBarHeight(50f);
        code39.setTextDisplayLocation(TextLocation.Bottom);
        code39.setTextColor(blue);
        point.setLocation(point.x, y);
        code39.draw(page, point);

        //保存PDF文档
        doc.saveToFile("/Users/snipe/Documents/temp/output/DrawBarcode.pdf");
    }


    /**
     * java 调用网络打印机直接打印
     *
     * @Author created by barrett in 2023/6/27 09:46
     */
    @Test
    public void print() throws PrinterException {

        //创建Workbook类的对象
        Workbook workbook = new Workbook();

        //载入Excel文件
        workbook.loadFromFile("/Users/snipe/Documents/temp/800.xlsm");
//        workbook.loadFromFile("/Users/snipe/Documents/temp/工作簿1.xlsx");

        //创建一个PrinterJob类的对象
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        //指定打印机名称（实际测试中未获取到打印机的ip）
        PrintService myPrintService = findPrintService("192.168.0.21");
//        PrintService myPrintService = findPrintService("\\\\192.168.0.69\\192.168.0.21");
        printerJob.setPrintService(myPrintService);

        //创建一个PageFormat类的对象并将页面设置为默认大小和方向
        PageFormat pageFormat = printerJob.defaultPage();

        //通过此页面设置返回一个Paper类的对象
        Paper paper = pageFormat.getPaper();

        //设置纸张的可绘制区域
        paper.setImageableArea(0, 0, pageFormat.getWidth(), pageFormat.getHeight());

        //通过该Paper类的对象设置页面格式
        pageFormat.setPaper(paper);

        //设置打印份数
        printerJob.setCopies(1);

        //调用绘图器以指定的格式渲染工作簿
        printerJob.setPrintable(workbook, pageFormat);

        //执行打印
        try {
            printerJob.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    //通过打印机名获取打印服务
    private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrinterJob.lookupPrintServices();
        for (PrintService printService : printServices) {
            if (printService.getName().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }
}
