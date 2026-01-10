package org.mythicprojects.saga.exception;

public class SagaExecutionException extends Exception {
    
    public SagaExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
