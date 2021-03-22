package com.barrett.gof.观察者模式.demo;

public abstract class Observer {
   protected Subject subject;
   public abstract void update();
}