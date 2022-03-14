package com.barrett.gof.编程式事物封装;

import org.springframework.transaction.TransactionStatus;

public interface TranCallback<T> {
    T doInTransaction(TranStatus var);
}