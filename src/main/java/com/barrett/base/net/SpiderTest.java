package com.barrett.base.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 网络爬虫原理
 * 模拟浏览器，有些网站不能被下载
 *
 * @Author created by barrett in 2020/5/20 08:47
 */
public class SpiderTest {
    public static void main(String[] args) {

        test();
    }

    /**
     * @Description //TODO 抓取网页源码，并保存
     * @Author barrett
     * @Date 2020/8/2 09:00
     * @Param
     * @return
     **/
    static void test() {
        try (
                BufferedReader read =
                        new BufferedReader(//提高流处理的速度
                                new InputStreamReader(//字节流转换为字符流
                                        new URL("http://demo.qfpffmp.cn/cssthemes6/sads_4_tyjnb/index.html").openStream(), //节点流
                                        "utf-8"));
                //输出到文件中
                BufferedWriter writer =
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream("blog.html"), "utf-8"))
        ) {
            String msg = "";
            while ((msg = read.readLine()) != null) {
                writer.write(msg);
                writer.newLine();
//                System.out.print(msg);
            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void test1() {
        try {
            URL url = new URL("http://demo.qfpffmp.cn/cssthemes6/sads_4_tyjnb/index.html");
            final InputStream inputStream = url.openStream();
            final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String msg;
            while (null != (msg = br.readLine())) {
                System.out.println(msg);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * java.io.IOException: Server returned HTTP response code: 403 for URL: https://www.dianping.com 拒绝访问
     * 模拟浏览器发起请求，fixme-1 貌似依旧返回403
     * @Author created by barrett in 2020/5/20 08:57
     */
    static void test2() {
        try {
            URL url = new URL("https://www.dianping.com");
            HttpURLConnection conn = (HttpURLConnection) url.getContent();
            conn.setRequestMethod("GET");
            //浏览器打开f12,Request Headers中拷贝User-Agent
            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");
            final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String msg;
            while (null != (msg = br.readLine())) {
                System.out.println(msg);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
