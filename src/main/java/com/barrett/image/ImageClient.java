package com.barrett.image;

import java.io.File;
import java.io.FileOutputStream;
import net.coobird.thumbnailator.Thumbnails;

public class ImageClient {

  public static void main(String[] args) throws Exception {
    Thumbnails.of(new File("/Users/snipe/Downloads/1018.GIF"))
        .scale(0.5f) //图片大小（长宽）压缩比例 从0-1，1表示原图
        .outputQuality(0.5f) //图片质量压缩比例 从0-1，越接近1质量越好
        .toOutputStream(new FileOutputStream("/Users/snipe/Downloads/50.GIF"));
  }

}
