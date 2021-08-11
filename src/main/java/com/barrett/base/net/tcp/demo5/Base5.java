package com.barrett.base.net.tcp.demo5;

import com.barrett.base.net.tcp.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base5 {
    public static Logger log = LoggerFactory.getLogger(Base5.class);


    public void sleep(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
