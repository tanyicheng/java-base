package com.barrett.tcp;

import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 佐格温湿度计
 *
 * @author created by barrett in 2024/7/25 14:04
 */
public class ReadSensor {

    public static void main(String[] args) {
        while (true) {
            read();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 每次读取都新建连接，若需要实时读取因修改为长链接读取
     * @author created by barrett in 2024/7/25 14:05
     */
    public static void read() {
        try (Socket socket = new Socket("172.16.2.200", 2101)) {
            // 向服务端发送数据
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.println("AT*ReadSensor:0");
            writer.flush();

            // 接收服务端响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse;

            for (int i = 0; i < 7; i++) {
                serverResponse = reader.readLine();
                extractValueAndSplit(serverResponse);
            }

            //使用while 不会退出循环！！
//            while ((serverResponse = reader.readLine()) != null) {
////                System.out.println("收到服务端响应: " + serverResponse);
//                extractValueAndSplit(serverResponse);
//            }
            System.out.println("---结束循环---");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void start() {
        try {
            Socket socket = new Socket("172.16.2.200", 2101);

            new Thread(() -> {
                send(socket);
            }).start();

            new Thread(() -> {
                receive(socket);
            }).start();


            Thread.sleep(20*1000); // 阻塞主线程

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receive(Socket socket) {
        // 接收服务端响应
        try {
            System.out.println("等待接收 ---");
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse;
            while ((serverResponse = reader.readLine()) != null) {
                extractValueAndSplit(serverResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(Socket socket) {
        try {
            // 向服务端发送数据
            while (true) {
                Thread.sleep(2000);
                System.out.println("send ---");
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                writer.println("AT*ReadSensor:0");
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对返回结果解析
     * AT*ReadSensor
     *
     * Air pressure = 0.0HPa
     * Humidity = 56.1%
     * Temperature = 27.1C
     *
     * OK!
     */
    public static String extractValueAndSplit(String response) {
        // 使用正则表达式匹配等号后面的值
        Pattern pattern = Pattern.compile("=(.*)");
        Matcher matcher = pattern.matcher(response);

        if (matcher.find()) {
            String value = matcher.group(1).trim();

            // 再使用正则表达式拆分数字和单位
            Pattern numberPattern = Pattern.compile("([0-9.]+)(.*)");
            Matcher numberMatcher = numberPattern.matcher(value);

            if (numberMatcher.find()) {
                String number = numberMatcher.group(1);
                String unit = numberMatcher.group(2);
                System.out.println("数字: " + number + ", 单位: " + unit);
                return number+"-"+unit;
            } else {
                System.out.println("无法拆分数字和单位");
            }
        }
        return "";
    }
}
