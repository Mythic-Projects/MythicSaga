package org.mythicprojects.saga;

import org.mythicprojects.saga.function.SagaFunction;
import org.mythicprojects.saga.execution.SagaTransactionBuilder;
import org.mythicprojects.saga.execution.SagaTransactionExecution;

public final class SagaOrchestrator {
    
    public <T> SagaTransaction<T> createTransaction(String id, SagaFunction<SagaTransactionExecution, T> execution) {
        return new SagaTransactionBuilder<>(id, execution);
    }

}

