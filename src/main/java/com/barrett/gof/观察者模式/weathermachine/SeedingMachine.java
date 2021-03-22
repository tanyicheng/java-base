package com.barrett.gof.观察者模式.weathermachine;

/**
 * @author Barrett
 */
public class SeedingMachine extends BaseObserver {
    public static final int TEMP = 5;

    public SeedingMachine(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void work() {
        if (subject.getTemp() > TEMP) {
            System.out.println("播种机运作");
        }
    }
}