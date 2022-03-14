package com.barrett.gof.编程式事物封装;

import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public abstract class AbsPlatformTranManager implements PlatformTranManager {


    @Override
    public TranStatus getTransaction(TranDefinition var1) throws TransactionException {
        System.out.println("获取 getTransaction");
        return null;
    }

    @Override
    public void commit(TranStatus var1) throws TransactionException {
        System.out.println("提交事物");

    }

    @Override
    public void rollback(TranStatus var1) throws TransactionException {
        System.out.println("回滚事物");

    }
}
