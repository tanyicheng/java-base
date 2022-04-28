package com.barrett.base.webservices.server;

import javax.xml.ws.Endpoint;

public class Main {

    /**
     * 到此为止，服务器端程序创建成功，运行后可在浏览器中访问 http://localhost:8085/ws_server/weather?wsdl 查看是否发布成功。
     * @author created by barrett in 2020/11/18 16:10
     **/
    public static void main(String[] args) {
        //发布天气服务，这里只是简单的测试
        Endpoint.publish("http://localhost:8085/ws_server/weather", new WeatherServiceImpl());
        System.out.println("发布天气服务成功...");
    }
}
