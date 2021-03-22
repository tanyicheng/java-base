package com.barrett.gof.观察者模式.weathermachine;

/**
 * 参加83行代码重构
 * @author created by barrett in 2021/3/22 23:02
 **/
public class ObserverPatternDemo {
   public static void main(String[] args) {
      Subject subject = new Subject();

      new ReapingMachine(subject);
      new WateringMachine(subject);
      new SeedingMachine(subject);
      new NewMachine(subject);

//      subject.setTemp(15);
//      subject.setHumidity(65);
      subject.setParams(31,65,3);
   }
}