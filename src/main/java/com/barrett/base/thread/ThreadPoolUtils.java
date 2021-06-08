package com.barrett.base.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {

    /**
     * 线程池
     */
    public final static ExecutorService executor = Executors.newCachedThreadPool();


}