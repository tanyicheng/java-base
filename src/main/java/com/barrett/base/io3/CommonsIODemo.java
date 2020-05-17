package com.barrett.base.io3;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 基本使用
 *
 * @Author created by barrett in 2020/5/17 20:27
 */
public class CommonsIODemo {

    public static void main(String[] args) {
//        test1();
//        read();
//        write();
        copy();
    }

    //拷贝
    static void copy(){
        try {
            //拷贝文件
//            FileUtils.copyFile(new File("README.md"),new File("README-copy.md"));
            //拷贝文件到目录
//            FileUtils.copyFileToDirectory(new File("README.md"),new File("gradle"));
            //拷贝目录
//            FileUtils.copyDirectory(new File("gradle"),new File("gradle"));
            //拷贝目录到目录
//            FileUtils.copyDirectoryToDirectory(new File("gradle"),new File("gradle-copy"));
            //拷贝url的内容
//            FileUtils.copyURLToFile(new URL("http://www.baidu.com"),new File("test.txt"));
            //获取字符
            final String s = IOUtils.toString(new URL("http://www.baidu.com"), "utf-8");
            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //写入内容
    static void write(){
        try {
            FileUtils.write(new File("happy.txt"),"学无止境\n","utf-8");
            //同上
            FileUtils.writeStringToFile(new File("happy.txt"),"书中自有颜如玉\n","utf-8",true);
            FileUtils.writeByteArrayToFile(new File("happy.txt"),"书中自有黄金屋\n".getBytes("utf-8"),true);

            //批量逐行写入
            List<String> list = new ArrayList<>();
            list.add("AAA");
            list.add("BBB");
            list.add("CCC");

            FileUtils.writeLines(new File("happy.txt"),list," | ",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //读取内容
    static void read() {
        //读取文件
        try {
            String s = FileUtils.readFileToString(new File(".gitignore"), "utf-8");
            System.out.println(s);
            final byte[] bytes = FileUtils.readFileToByteArray(new File(".gitignore"));
            System.out.println(bytes.length);

            System.out.println("----------------------------");
            //逐行读取
            final List<String> strings = FileUtils.readLines(new File(".gitignore"), "utf-8");
            for (String string : strings) {
                System.out.println(string);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //文件、目录大小
    static void test1() {
        //文件大小
        long len = FileUtils.sizeOf(new File("src/main/java/com/barrett/base/io2/CopyTxtDemo.java"));
        System.out.println(len);
        //目录大小
        long size = FileUtils.sizeOf(new File("src/main/java/com/barrett/base/io2"));
        System.out.println(size);
    }

    //列出子孙级
    static void test2() {
//        Collection<File> files = FileUtils.listFiles(
//                new File("src/main/java/com/barrett"),
//                EmptyFileFilter.NOT_EMPTY, //过滤文件非空
//                DirectoryFileFilter.INSTANCE);//查看子孙级
//        for (File file : files) {
//            System.out.println(file.getAbsolutePath());
//        }

        Collection<File> files2 = FileUtils.listFiles(
                new File("D:/git/my_study/java-base"),
                FileFilterUtils.or(new SuffixFileFilter("java"), new SuffixFileFilter("class")), //只需要java\class文件的
                DirectoryFileFilter.INSTANCE);//查看子孙级

        for (File file : files2) {
            System.out.println(file.getAbsolutePath());
        }


    }
}
