package org.mythicprojects.saga;

import org.jetbrains.annotations.NotNull;

public interface SagaTransaction<T> extends SagaComponent<T, SagaTransaction<T>> {
    
    @Override
    default @NotNull ComponentType type() {
        return ComponentType.TRANSACTION;
    }
}
