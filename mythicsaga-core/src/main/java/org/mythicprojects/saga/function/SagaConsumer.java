package org.mythicprojects.saga.function;

import org.jetbrains.annotations.Blocking;

public interface SagaConsumer<T> {
    
    @Blocking
    void accept(T t) throws Throwable;
}
