package com.barrett.base.io.bio.demo1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Utils {
    private static final String HTTP_SEPARATOR = "\r\n";
    public static final int PORT = 8888;
    public static final int BACK_LOG = 1024;

    public static String buildHttpResp() {
        StringBuffer sb = new StringBuffer();
        String str = "<h1>Hello world</h1>";
        sb.append("HTTP/1.1 200 OK").append(HTTP_SEPARATOR);
        sb.append("connection: Close").append(HTTP_SEPARATOR);
        sb.append("content-type: text/html").append(HTTP_SEPARATOR);
        sb.append("content-length: " + str.length()).append(HTTP_SEPARATOR);
        sb.append(HTTP_SEPARATOR);
        sb.append(str);
        return sb.toString();
    }

    public static ThreadPoolExecutor buildThreadPoolExecutor() {
        return new ThreadPoolExecutor(100, 100, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
    }
    public static BufferedWriter buildBuffereWriter(OutputStream outputStream){
        return new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    public static BufferedReader buildBuffereReader(InputStream inputStream){
        return new BufferedReader(new InputStreamReader(inputStream));
    }


    public static void doSomeWork(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
