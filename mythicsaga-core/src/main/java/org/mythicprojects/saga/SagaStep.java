package org.mythicprojects.saga;

import org.jetbrains.annotations.NotNull;

public interface SagaStep<T> extends SagaComponent<T, SagaStep<T>> {
    
    @Override
    default @NotNull ComponentType type() {
        return ComponentType.STEP;
    }
}
