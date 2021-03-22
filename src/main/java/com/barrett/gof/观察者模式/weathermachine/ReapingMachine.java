package com.barrett.gof.观察者模式.weathermachine;


/**
 * @author Barrett
 */
public class ReapingMachine extends BaseObserver {

    private static final int TEMP = 31;
    private static final int HUMIDITY = 61;

    public ReapingMachine(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void work() {
        if (subject.getTemp() > TEMP && subject.getHumidity() > HUMIDITY) {
            System.out.println("收割机运作");
        }
    }
}