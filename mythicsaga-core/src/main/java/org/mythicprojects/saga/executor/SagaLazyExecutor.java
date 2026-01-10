package org.mythicprojects.saga.executor;

import java.util.concurrent.Executor;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

final class SagaLazyExecutor extends SagaExecutorBase {
    
    private final Supplier<? extends Executor> executorSupplier;

    SagaLazyExecutor(Supplier<? extends Executor> executorSupplier) {
        this.executorSupplier = executorSupplier;
    }

    @Override
    protected @NotNull Executor getExecutor() {
        return this.executorSupplier.get();
    }
    
}
