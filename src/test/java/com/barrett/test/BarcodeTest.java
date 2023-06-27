package com.barrett.test;

import com.barrett.util.DateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.*;

import static com.barrett.util.qrcode.BarcodeUtils.generateFile;

public class BarcodeTest {

    @Test
    public void createBarcode() {
//        String msg = "BAR" + DateUtil.getDateTimeToOrder(0);
        String msg = "BM7872230491201774";
        String path = "/Users/snipe/Documents/temp/output/barcodes/" + msg + ".png";
        generateFile(msg, path);
    }

    @Test
    public void createBarcodeMore() {
        for (int i = 0; i < 30; i++) {
            String msg = "BAR" + DateUtil.getDate() + "-" + i;
            String path = "/Users/snipe/Documents/temp/output/barcodes/" + msg + ".png";
            generateFile(msg, path);
        }
    }

}
