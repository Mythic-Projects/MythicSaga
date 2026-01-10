package org.mythicprojects.saga.function;

import org.jetbrains.annotations.Blocking;

public interface SagaSupplier<T> {
    
    @Blocking
    T get() throws Throwable;
}
