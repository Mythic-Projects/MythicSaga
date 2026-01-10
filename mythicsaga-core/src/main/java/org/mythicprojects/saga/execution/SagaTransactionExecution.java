package org.mythicprojects.saga.execution;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mythicprojects.saga.SagaStep;
import org.mythicprojects.saga.function.SagaRunnable;
import org.mythicprojects.saga.function.SagaSupplier;

public interface SagaTransactionExecution {
    
    @Contract(pure = true)
    <T> @NotNull SagaStep<T> createStep(@NotNull String id, @NotNull SagaSupplier<T> supplier);
    
    @Contract(pure = true)
    default @NotNull SagaStep<Void> createStep(@NotNull String id, @NotNull SagaRunnable runnable) {
        return this.createStep(id, () -> {
            runnable.run();
            return null;
        });
    }
}
