package com.barrett.test;


import com.barrett.base.webservices.server.IWeatherService;
import com.barrett.base.webservices.server.WeatherServiceImpl;
import com.barrett.base.webservices.server.WeatherServiceImplService;

/**
 *  WebServer客户端测试
 * <p>
 * 创建人：asus <br>
 * 创建时间：2019-01-14 14:49 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class ClientTest {

    /**
     * 一般在另一个调用方
     * 正式调用 src/main/java目录
     * 测试src/test/java目录下执行：wsimport -keep http://localhost:8085/ws_server/weather?wsdl
     * @author created by barrett in 2020/11/18 16:10
     **/
    public static void main(String[] args) {
        WeatherServiceImplService factory = new WeatherServiceImplService();
        WeatherServiceImpl serviceImplPort = factory.getWeatherServiceImplPort();
        String weather = serviceImplPort.getWeatherByCityname("深圳");
        System.out.println(weather);
    }

}