package com.barrett.gof.编程式事物封装;

public interface TranStatus {
    boolean hasSavepoint();

    void flush();
}