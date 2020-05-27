package com.barrett.base.net.tcp;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {

    /**
     * 封装释放资源的方法
     * @Author created by barrett in 2020/5/27 20:34
     */
    public static void close(Closeable... targets){
        for (Closeable target : targets) {
            if(null != target) {
                try {
                    target.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


