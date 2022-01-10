package com.barrett.util.waterMark;

import java.awt.FontMetrics;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JLabel;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * TODO 参考 https://blog.csdn.net/luckykapok918/article/details/73088978
 * 获取PDF页面高度、宽度然后进行动态定位，比如说根据页面宽度实现平铺水印：
 * @author created by barrett in 2021/12/16 19:26
 **/
public class TestWaterPrint1 {
    public static void main(String[] args) throws DocumentException, IOException {
        // 要输出的pdf文件
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("E:\\Temp\\9demo_1.pdf")));
        // 将pdf文件先加水印然后输出
        setWatermark(bos, "E:\\Temp\\9demo.pdf", "测试user");
    }

    /**
     * 
     * @param bos输出文件的位置
     * @param input
     *            原PDF位置
     * @param waterMarkName
     *            页脚添加水印
     * @throws DocumentException
     * @throws IOException
     */
    public static void setWatermark(BufferedOutputStream bos, String input, String waterMarkName)
            throws DocumentException, IOException {

        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, bos);

        // 获取总页数 +1, 下面从1开始遍历
        int total = reader.getNumberOfPages() + 1;
        BaseFont base = null;
        try {
            // 使用classpath下面的字体库
//            base = BaseFont.createFont("/calibri.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            //win下字体
            base = BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", com.lowagie.text.pdf.BaseFont.IDENTITY_H, com.lowagie.text.pdf.BaseFont.NOT_EMBEDDED);

        } catch (Exception e) {
            // 日志处理
            e.printStackTrace();
        }

        // 间隔
        int interval = -5;
        // 获取水印文字的高度和宽度
        int textH = 0, textW = 0;
        JLabel label = new JLabel();
        label.setText(waterMarkName);
        FontMetrics metrics = label.getFontMetrics(label.getFont());
        textH = metrics.getHeight();
        textW = metrics.stringWidth(label.getText());
        System.out.println("textH: " + textH);
        System.out.println("textW: " + textW);

        // 设置水印透明度
        PdfGState gs = new PdfGState();
        gs.setFillOpacity(0.4f);
        gs.setStrokeOpacity(0.4f);

        Rectangle pageSizeWithRotation = null;
        PdfContentByte content = null;
        for (int i = 1; i < total; i++) {
            // 在内容上方加水印
            content = stamper.getOverContent(i);
            // 在内容下方加水印
            // content = stamper.getUnderContent(i);
            content.saveState();
            content.setGState(gs);

            // 设置字体和字体大小
            content.beginText();
            content.setFontAndSize(base, 20);

            // 获取每一页的高度、宽度
            pageSizeWithRotation = reader.getPageSizeWithRotation(i);
            float pageHeight = pageSizeWithRotation.getHeight();
            float pageWidth = pageSizeWithRotation.getWidth();

            // 根据纸张大小多次添加， 水印文字成30度角倾斜
            for (int height = interval + textH; height < pageHeight; height = height + textH * 3) {
                for (int width = interval + textW; width < pageWidth + textW; width = width + textW * 2) {
                    content.showTextAligned(Element.ALIGN_LEFT, waterMarkName, width - textW, height - textH, 30);
                }
            }

            content.endText();
        }

        // 关流
        stamper.close();
        reader.close();
    }
}