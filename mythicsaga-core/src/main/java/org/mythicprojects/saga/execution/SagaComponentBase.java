package org.mythicprojects.saga.execution;

import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NotNull;
import org.mythicprojects.saga.SagaComponent;
import org.mythicprojects.saga.exception.SagaExecutionException;
import org.mythicprojects.saga.exception.SagaRollbackException;
import org.mythicprojects.saga.function.SagaRunnable;

abstract class SagaComponentBase<T, C extends SagaComponent<T, C>> implements SagaComponent<T, C> {

    private final String id;

    private SagaRunnable rollbackHandler;

    SagaComponentBase(@NotNull String id) {
        this.id = id;
    }

    @Override
    public @NotNull String id() {
        return this.id;
    }

    @Override
    public C onRollback(@NotNull SagaRunnable rollbackHandler) {
        this.rollbackHandler = rollbackHandler;
        return this.self();
    }

    @Blocking
    abstract T internalExecute() throws Throwable;

    @Blocking
    @Override
    public T execute() throws SagaExecutionException {
        try {
            return this.internalExecute();
        }
        catch (SagaExecutionException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new SagaExecutionException(
                    this + " execution failed",
                    ex
            );
        }
    }

    void executeRollbackHandler() throws SagaRollbackException {
        if (this.rollbackHandler == null) {
            return;
        }
        try {
            this.rollbackHandler.run();
        }
        catch (Throwable ex) {
            throw new SagaRollbackException(
                    this + " rollback handler failed",
                    ex
            );
        }
    }

    @Override
    public String toString() {
        return "Saga " + this.type() + " [ID: " + this.id() + "]";
    }

}
