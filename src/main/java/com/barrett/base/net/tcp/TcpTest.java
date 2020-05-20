package com.barrett.base.net.tcp;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress 类的使用 解析DNS获取ip地址
 * @Author created by barrett in 2020/5/19 11:01
 */
public class TcpTest {

    public static void main(String[] args) throws Exception {
        InetAddress add = InetAddress.getLocalHost();
        System.out.println(add.getHostAddress());
        System.out.println(add.getHostName());


        final InetAddress name = InetAddress.getByName("www.baidu.com");
        System.out.println(name.getHostAddress());
    }
}
