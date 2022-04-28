package com.barrett.base.webservices.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * WebService天气服务接口
 * <p>
 * 创建人：asus <br>
 * 创建时间：2019-01-14 14:24 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
@WebService
public interface IWeatherService {

    @WebMethod
    String getWeatherByCityname(String name);

}