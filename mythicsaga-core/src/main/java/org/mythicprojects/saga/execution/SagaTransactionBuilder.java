package org.mythicprojects.saga.execution;

import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NotNull;
import org.mythicprojects.saga.SagaTransaction;
import org.mythicprojects.saga.function.SagaFunction;

public final class SagaTransactionBuilder<T> extends SagaComponentBase<T, SagaTransaction<T>> implements SagaTransaction<T> {

    private final SagaFunction<? super SagaTransactionExecution, ? extends T> execution;

    public SagaTransactionBuilder(
            @NotNull String name,
            SagaFunction<? super SagaTransactionExecution, ? extends T> execution
    ) {
        super(name);
        this.execution = execution;
    }

    @Blocking
    @Override
    T internalExecute() throws Throwable {
        SagaTransactionExecutionImpl transactionExecution = new SagaTransactionExecutionImpl();
        transactionExecution.addRollbackHandler(this);
        
        try {
            return this.execution.apply(transactionExecution);
        } catch (Throwable ex) {
            transactionExecution.rollback(ex);
            throw ex;
        }
    }
}
