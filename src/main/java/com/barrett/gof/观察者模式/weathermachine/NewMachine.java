package com.barrett.gof.观察者模式.weathermachine;

/**
 * 新添加的机器
 * @author created by barrett in 2021/3/22 22:29
 **/
public class NewMachine extends BaseObserver {

    private  static final int TEMP = 31;
    private  static final int HUMIDITY = 61;

    public NewMachine(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void work() {
        if (subject.getTemp() > TEMP && subject.getHumidity() > HUMIDITY) {
            System.out.println("新机器运作");
        }
    }
}