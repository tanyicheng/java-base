package com.barrett.process;

import org.junit.Test;

import java.io.IOException;

/**
 * 进程 test
 * @author created by barrett in 2021/7/12 08:52
 **/
public class KillProcess {

    @Test
    public void getProcess() throws IOException {
        //启动应用
        Process process = Runtime.getRuntime().exec("D:\\package\\plc-updown\\流水线上下料客户端v1.0.2.exe");

        System.out.println(process);
    }
}
