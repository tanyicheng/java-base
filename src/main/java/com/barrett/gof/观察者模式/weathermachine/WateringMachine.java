package com.barrett.gof.观察者模式.weathermachine;

/**
 * @author Barrett
 */
public class WateringMachine extends BaseObserver {


    private static final int TEMP = 31;
    private  static final int HUMIDITY = 61;
    private  static final int WIND_POWER = 3;

    public WateringMachine(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void work() {
        if (subject.getTemp() > TEMP && subject.getHumidity() < HUMIDITY && subject.getWindPower() < WIND_POWER) {
            System.out.println("喷灌机运作");
        }
    }
}