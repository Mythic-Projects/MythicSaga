package org.mythicprojects.saga.exception;

public class SagaRollbackException extends SagaExecutionException {

    public SagaRollbackException(String message, Throwable cause) {
        super(message, cause);
    }

}

