package com.barrett.gof.编程式事物封装;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;

import java.lang.reflect.UndeclaredThrowableException;

public class TranTemplate implements TranDefinition {
    private static final Logger logger = LoggerFactory.getLogger(TranTemplate.class);

    private PlatformTranManager transactionManager = new DataTranManager();


    public <T> T execute(TranCallback<T> action) throws TransactionException {

        System.out.println("execute start");
        TranStatus status = this.transactionManager.getTransaction(this);
        T result;
        try {
            result = action.doInTransaction(status);
        } catch (RuntimeException var5) {
            this.rollbackOnException(status, var5);
            throw var5;
        } catch (Error var6) {
            this.rollbackOnException(status, var6);
            throw var6;
        } catch (Throwable var7) {
            this.rollbackOnException(status, var7);
            throw new UndeclaredThrowableException(var7, "TransactionCallback threw undeclared checked exception");
        }

        this.transactionManager.commit(status);
        return result;

    }

    private void rollbackOnException(TranStatus status, Throwable ex) throws TransactionException {
        logger.debug("Initiating transaction rollback on application exception", ex);

        try {
            this.transactionManager.rollback(status);
        } catch (TransactionSystemException var4) {
            logger.error("Application exception overridden by rollback exception", ex);
            var4.initApplicationException(ex);
            throw var4;
        } catch (RuntimeException var5) {
            logger.error("Application exception overridden by rollback exception", ex);
            throw var5;
        } catch (Error var6) {
            logger.error("Application exception overridden by rollback error", ex);
            throw var6;
        }
    }


}
