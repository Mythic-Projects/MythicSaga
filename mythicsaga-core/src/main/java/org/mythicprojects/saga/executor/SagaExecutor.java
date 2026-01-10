package org.mythicprojects.saga.executor;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mythicprojects.saga.function.SagaConsumer;
import org.mythicprojects.saga.function.SagaFunction;
import org.mythicprojects.saga.function.SagaRunnable;
import org.mythicprojects.saga.function.SagaSupplier;

/**
 * Wrapper for executors to wrap various functional interfaces.
 */
public interface SagaExecutor {
    
    @Contract(pure = true)
    default @NotNull SagaExecutor create(Executor executor) {
        return new SagaStaticExecutor(executor);
    }
    
    @Contract(pure = true)
    default @NotNull SagaExecutor create(Supplier<? extends Executor> executorSupplier) {
        return new SagaLazyExecutor(executorSupplier);
    }
    
    @NotNull SagaRunnable wrap(@NotNull Runnable runnable);
    
    @NotNull SagaRunnable wrapThrowing(@NotNull SagaRunnable runnable);
    
    <T> @NotNull SagaSupplier<T> wrap(@NotNull Supplier<T> supplier);
    
    <T> @NotNull SagaSupplier<T> wrapThrowing(@NotNull SagaSupplier<T> supplier);
    
    <T> @NotNull SagaConsumer<T> wrap(@NotNull Consumer<T> consumer);
    
    <T> @NotNull SagaConsumer<T> wrapThrowing(@NotNull SagaConsumer<T> consumer);
    
    <T, R> @NotNull SagaFunction<T, R> wrap(@NotNull Function<T, R> function);
    
    <T, R> @NotNull SagaFunction<T, R> wrapThrowing(@NotNull SagaFunction<T, R> function);
}
