package org.mythicprojects.saga;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mythicprojects.saga.exception.SagaExecutionException;
import org.mythicprojects.saga.function.SagaRunnable;

public interface SagaComponent<T, C extends SagaComponent<T, C>> {
    
    @NotNull ComponentType type();
    
    @NotNull String name();
    
    @Contract("_ -> this")
    C onRollback(@NotNull SagaRunnable rollbackHandler);

    @Blocking
    T execute() throws SagaExecutionException;

    @ApiStatus.Internal
    default @NotNull C self() {
        @SuppressWarnings("unchecked")
        C self = (C) this;
        return self;
    }
    
    enum ComponentType {
        STEP,
        TRANSACTION
    }
}

