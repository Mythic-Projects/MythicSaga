package org.mythicprojects.saga.function;

import org.jetbrains.annotations.Blocking;

public interface SagaFunction<T, R> {
    
    @Blocking
    R apply(T t) throws Throwable;
}
