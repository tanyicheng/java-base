package com.barrett.base.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

        test2();
    }

    static void test1() {
        try {
            URL url = new URL("https://www.jd.com");
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
