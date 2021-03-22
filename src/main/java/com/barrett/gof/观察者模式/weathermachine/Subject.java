package com.barrett.gof.观察者模式.weathermachine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Barrett
 */
public class Subject {

    private List<BaseObserver> baseObservers = new ArrayList<BaseObserver>();
    private int temp;
    private int humidity;
    private int windPower;

    public void setParams(int temp, int humidity, int windPower) {
        this.temp = temp;
        this.humidity = humidity;
        this.windPower = windPower;
        notifyAllObservers();
    }


    /**
     * 添加到观察者的集合中
     * @author created by barrett in 2021/3/22 22:43
     **/
    public void attach(BaseObserver baseObserver) {
        baseObservers.add(baseObserver);
    }

    /**
     * 通知所有观察者
     *
     * @author created by barrett in 2021/3/22 22:10
     **/
    public void notifyAllObservers() {
        for (BaseObserver baseObserver : baseObservers) {
            baseObserver.work();
        }
    }


    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
        notifyAllObservers();
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
        notifyAllObservers();
    }

    public int getWindPower() {
        return windPower;
    }

    public void setWindPower(int windPower) {
        this.windPower = windPower;
        notifyAllObservers();
    }

}