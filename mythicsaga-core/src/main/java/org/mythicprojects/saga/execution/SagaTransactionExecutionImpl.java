package org.mythicprojects.saga.execution;

import java.util.ArrayDeque;
import java.util.Deque;
import org.jetbrains.annotations.NotNull;
import org.mythicprojects.saga.SagaStep;
import org.mythicprojects.saga.function.SagaRunnable;
import org.mythicprojects.saga.function.SagaSupplier;

final class SagaTransactionExecutionImpl implements SagaTransactionExecution {
    
    private final Deque<SagaRunnable> rollbackHandlers = new ArrayDeque<>();

    @Override
    public <S> @NotNull SagaStep<S> createStep(
            @NotNull String id,
            @NotNull SagaSupplier<S> supplier
    ) {
        return new SagaStepBuilder<>(id, supplier) {
            @Override
            S internalExecute() throws Throwable {
                S result = super.internalExecute();
                SagaTransactionExecutionImpl.this.addRollbackHandler(this);
                return result;
            }
        };
    }
    
    void addRollbackHandler(@NotNull SagaRunnable handler) {
        this.rollbackHandlers.push(handler);
    }
    
    void addRollbackHandler(@NotNull SagaComponentBase<?, ?> component) {
        this.rollbackHandlers.push(component::executeRollbackHandler);
    }

    void rollback(@NotNull Throwable primaryException) {
        for (SagaRunnable handler : this.rollbackHandlers) {
            try {
                handler.run();
            } catch (Throwable rollbackException) {
                primaryException.addSuppressed(rollbackException);
            }
        }
    }
}

