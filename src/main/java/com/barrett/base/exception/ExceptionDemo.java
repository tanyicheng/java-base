package com.barrett.base.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 捕获主要异常
 * @Author created by barrett in 2023/1/17 14:48
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            System.out.println(1/0);
        } catch (Exception e) {
//            final Writer result = new StringWriter();
//            final PrintWriter printWriter = new PrintWriter(result);
//            e.printStackTrace(printWriter);
//            String stackTraceStr = result.toString();
//            System.out.println(stackTraceStr);

            for (String frame : ExceptionUtils.getStackFrames(e)) {
                System.out.println(frame);
            }
        }

        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            StringBuilder str = new StringBuilder();
            String[] frameArr = ExceptionUtils.getStackFrames(e);
            for (int i = 0; i < frameArr.length; i++) {
                String frame = frameArr[i];
                if (i == 0 || frame.contains("org.jeecg.modules")) {
                    str.append(frame).append("\n");
                }
            }

            System.out.println(str);
//            final Writer result = new StringWriter();
//            final PrintWriter printWriter = new PrintWriter(result);
//            e.printStackTrace(printWriter);
//            String stackTraceStr = result.toString();
//            System.out.println(stackTraceStr);
        }
    }


}
