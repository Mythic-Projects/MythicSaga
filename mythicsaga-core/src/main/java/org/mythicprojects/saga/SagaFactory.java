package org.mythicprojects.saga;

import org.mythicprojects.saga.execution.SagaTransactionBuilder;
import org.mythicprojects.saga.execution.SagaTransactionExecution;
import org.mythicprojects.saga.function.SagaFunction;

public final class SagaFactory {
    
    public <T> SagaTransaction<T> createTransaction(String name, SagaFunction<SagaTransactionExecution, T> execution) {
        return new SagaTransactionBuilder<>(name, execution);
    }

}

