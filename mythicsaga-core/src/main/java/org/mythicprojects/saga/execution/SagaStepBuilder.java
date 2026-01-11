package org.mythicprojects.saga.execution;

import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NotNull;
import org.mythicprojects.saga.SagaStep;
import org.mythicprojects.saga.function.SagaSupplier;

class SagaStepBuilder<T> extends SagaComponentBase<T, SagaStep<T>> implements SagaStep<T> {

    private final @NotNull SagaSupplier<? extends T> supplier;

    SagaStepBuilder(
            @NotNull String name,
            @NotNull SagaSupplier<? extends T> supplier
    ) {
        super(name);
        this.supplier = supplier;
    }

    @Blocking
    @Override
    T internalExecute() throws Throwable {
        return this.supplier.get();
    }

}

