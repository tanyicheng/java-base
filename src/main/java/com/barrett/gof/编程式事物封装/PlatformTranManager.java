package com.barrett.gof.编程式事物封装;

import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public interface PlatformTranManager {
    TranStatus getTransaction(TranDefinition var1) throws TransactionException;

    void commit(TranStatus var1) throws TransactionException;

    void rollback(TranStatus var1) throws TransactionException;
}
