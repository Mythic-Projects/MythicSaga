package org.mythicprojects.saga.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.mythicprojects.saga.function.SagaConsumer;
import org.mythicprojects.saga.function.SagaFunction;
import org.mythicprojects.saga.function.SagaRunnable;
import org.mythicprojects.saga.function.SagaSupplier;

public abstract class SagaExecutorBase implements SagaExecutor {

    protected abstract @NotNull Executor getExecutor();

    @Override
    public @NotNull SagaRunnable wrap(@NotNull Runnable runnable) {
        return this.wrapThrowing(runnable::run);
    }

    @Override
    public @NotNull SagaRunnable wrapThrowing(@NotNull SagaRunnable runnable) {
        return () -> this.executeAndGet(() -> {
            runnable.run();
            return null;
        });
    }

    @Override
    public <T> @NotNull SagaSupplier<T> wrap(@NotNull Supplier<T> supplier) {
        return this.wrapThrowing(supplier::get);
    }

    @Override
    public <T> @NotNull SagaSupplier<T> wrapThrowing(@NotNull SagaSupplier<T> supplier) {
        return () -> this.executeAndGet(supplier);
    }

    @Override
    public <T> @NotNull SagaConsumer<T> wrap(@NotNull Consumer<T> consumer) {
        return this.wrapThrowing(consumer::accept);
    }

    @Override
    public <T> @NotNull SagaConsumer<T> wrapThrowing(@NotNull SagaConsumer<T> consumer) {
        return input -> this.executeAndGet(() -> {
            consumer.accept(input);
            return null;
        });
    }

    @Override
    public <T, R> @NotNull SagaFunction<T, R> wrap(@NotNull Function<T, R> function) {
        return this.wrapThrowing(function::apply);
    }

    @Override
    public <T, R> @NotNull SagaFunction<T, R> wrapThrowing(@NotNull SagaFunction<T, R> function) {
        return input -> this.executeAndGet(() -> function.apply(input));
    }

    private <T> T executeAndGet(@NotNull SagaSupplier<? extends T> action) throws Throwable {
        CompletableFuture<T> future = new CompletableFuture<>();
        this.getExecutor().execute(() -> {
            try {
                T result = action.get();
                future.complete(result);
            }
            catch (Throwable ex) {
                future.completeExceptionally(ex);
            }
        });
        try {
            return future.join();
        }
        catch (CompletionException ex) {
            Throwable cause = ex.getCause();
            if (ex.getMessage() != null) {
                throw ex;
            }
            if (cause != null) {
                throw cause;
            }
            throw ex;
        }
    }
}
