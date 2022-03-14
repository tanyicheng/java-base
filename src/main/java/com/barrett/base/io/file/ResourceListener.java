package com.barrett.base.io.file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * nio 监听文件夹变化
 * @Param
 * @return
 * @Author created by barrett in 2022/2/8 22:43
 */
public class ResourceListener {
    private static ExecutorService fixedThreadPool = Executors.newCachedThreadPool();
    private WatchService ws;
    private String listenerPath;
    private ResourceListener(String path) {
        try {
            ws = FileSystems.getDefault().newWatchService();
            this.listenerPath = path;
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {
        fixedThreadPool.execute(new Listner(ws,this.listenerPath));
    }

    public static void addListener(String path) throws IOException {
        ResourceListener resourceListener = new ResourceListener(path);
        Path p = Paths.get(path);
        p.register(resourceListener.ws,StandardWatchEventKinds.ENTRY_MODIFY,
            StandardWatchEventKinds.ENTRY_DELETE,
            StandardWatchEventKinds.ENTRY_CREATE);
    }
    

    public static void main(String[] args) throws IOException {
        ResourceListener.addListener("F:\\");
        ResourceListener.addListener("d:\\");
    }
}

class Listner implements Runnable {
    private WatchService service;
    private String rootPath;
    
    public Listner(WatchService service,String rootPath) {
        this.service = service;
        this.rootPath = rootPath;
    }

    @Override
    public void run() {
        try {
            while(true){
                WatchKey watchKey = service.take();
                List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                for(WatchEvent<?> event : watchEvents){
                
                    //TODO 根据事件类型采取不同的操作。。。。。。。
                    System.out.println("["+rootPath+event.context()+"]文件发生了["+event.kind()+"]事件"+    event.count());  
                }
                watchKey.reset();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            System.out.println("fdsfsdf");
            try {
                service.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
}