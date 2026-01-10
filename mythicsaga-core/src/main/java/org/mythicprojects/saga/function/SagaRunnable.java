package org.mythicprojects.saga.function;

import org.jetbrains.annotations.Blocking;

public interface SagaRunnable {
    
    @Blocking
    void run() throws Throwable;
}
