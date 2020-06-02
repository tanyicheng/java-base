package com.barrett.base.thread;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.*;

public class ThreadJuc implements Callable<Boolean> {
    private String url;
    private String name;

    public ThreadJuc(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        FileUtils.copyURLToFile(new URL(url),new File(name));
        System.out.println(name);
        return true;
    }

    public static void main(String[] args) {
        ThreadJuc a = new ThreadJuc("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3238750427,2677534545&fm=15&gp=0.jpg", "a.jpg");
        ThreadJuc b = new ThreadJuc("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591119476742&di=97b2c542291b3ee43c3531eea623c00e&imgtype=0&src=http%3A%2F%2Fimg3.mukewang.com%2F5bd7c0850001cd3005000225.jpg", "b.jpg");

        //创建执行服务
        ExecutorService es = Executors.newFixedThreadPool(3);
        //提交执行
         Future<Boolean> result1 = es.submit(a);
         Future<Boolean> result2 = es.submit(b);
         //获取结果
        try {
            Boolean ab = result1.get();
            Boolean ab2 = result2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //关闭服务
        es.shutdown();

    }
}
