package com.barrett.util.waterMark;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用 com.lowagie.text.pdf.BaseFont; 无法添加样式，Font可以添加样式，但是setFontAndSize方法不接受Font参数。所以只能变通实现：
 *
 * 在每页右下角生成倾斜水印
 * @author created by barrett in 2021/12/16 19:25
 **/
public class TestWaterPrint3 {
    public static void main(String[] args) throws DocumentException, IOException {
        // 要输出的pdf文件
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("E:\\Temp\\9demo_out.pdf")));
        // 将pdf文件先加水印然后输出
        setWatermark(bos, "E:\\Temp\\9demo.pdf", "测试user123456789");
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

        // 设置水印透明度
        PdfGState gs = new PdfGState();
        gs.setFillOpacity(0.4f);
        gs.setStrokeOpacity(0.4f);

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
            content.setFontAndSize(base, 10);

            // 设置字体样式
            float ta = 1F, tb = 0F, tc = 0F, td = 1F, tx = 0F, ty = 0F;
            // 设置加粗(加粗)
            ta += 0.25F;
            td += 0.05F;
            ty -= 0.2F;
            // 设置倾斜(倾斜程序自己改)
            tc += 0.8F;
            content.setTextMatrix(ta, tb, tc, td, tx, ty);

            // 设置相对于左下角位置(向右为x，向上为y)
            content.moveText(300F, 5F);
            // 显示text
            content.showText(waterMarkName);

            content.endText();
            content.stroke();
            content.restoreState();
        }

        // 关流
        stamper.close();
        reader.close();
    }
}