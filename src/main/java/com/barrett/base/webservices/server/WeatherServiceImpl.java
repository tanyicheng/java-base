package com.barrett.base.webservices.server;

import javax.jws.WebService;

/**
 * WebService天气服务实现类
 * <p>
 * 创建人：asus <br>
 * 创建时间：2019-01-14 14:26 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@WebService
public class WeatherServiceImpl implements IWeatherService {

    @Override
    public String getWeatherByCityname(String name) {
        return name + "天气晴朗！";
    }
}
