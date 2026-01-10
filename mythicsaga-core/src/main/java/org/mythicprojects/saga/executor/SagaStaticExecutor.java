package org.mythicprojects.saga.executor;

import java.util.concurrent.Executor;
import org.jetbrains.annotations.NotNull;

final class SagaStaticExecutor extends SagaExecutorBase {

    private final Executor executor;

    SagaStaticExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    protected void execute(@NotNull Runnable runnable) {
        this.executor.execute(runnable);
    }

}
