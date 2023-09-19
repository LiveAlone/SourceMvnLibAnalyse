package org.yqj.source.resilience4j.examples;

import io.github.resilience4j.core.IntervalFunction;
import io.github.resilience4j.core.functions.CheckedSupplier;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/9/19
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class RetryExample {
    public static void main(String[] args) {
        decorate();
    }

    public static void event() {
        // 1. 事件监听器
        RetryRegistry registry = RetryRegistry.ofDefaults();
        registry.getEventPublisher()
                .onEntryAdded(entryAddedEvent -> {
                    Retry addedRetry = entryAddedEvent.getAddedEntry();
                    log.info("Retry {} added", addedRetry.getName());
                })
                .onEntryRemoved(entryRemovedEvent -> {
                    Retry removedRetry = entryRemovedEvent.getRemovedEntry();
                    log.info("Retry {} removed", removedRetry.getName());
                });

        // 1. IntervalFunction 间隔函数,固定间隔，指数间隔，随机间隔
        IntervalFunction defaultWaitInterval = IntervalFunction.ofDefaults();
        IntervalFunction fixedWaitInterval = IntervalFunction.of(Duration.ofSeconds(5));
        IntervalFunction intervalWithExponentialBackoff = IntervalFunction.ofExponentialBackoff();
        IntervalFunction intervalWithCustomExponentialBackoff = IntervalFunction.ofExponentialBackoff(IntervalFunction.DEFAULT_INITIAL_INTERVAL, 2d);
        IntervalFunction randomWaitInterval = IntervalFunction.ofRandomized();

        RetryConfig retryConfig = RetryConfig.custom()
                .intervalFunction(intervalWithExponentialBackoff)
                .build();
    }

    public static void decorate() {
        Retry retry = Retry.ofDefaults("id");
        Supplier<String> retryableSupplier = Retry.decorateSupplier(retry, () -> {
            log.info("retry supplier execute");
            throw new RuntimeException("fail test");
        });

        Try<String> result = Try.ofSupplier(retryableSupplier)
                .recover((throwable) -> {
                    log.info("recover execute", throwable);
                    return "Hello world from recovery function";
                });
        System.out.println(result.get());
    }

    public static void create() {
        // 1. 默认创建Repository
        RetryRegistry retryRegistry = RetryRegistry.ofDefaults();

        // 2. 配置创建
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(2)
                .waitDuration(Duration.ofMillis(1000))
//                .retryOnResult(response -> response.getStatus() == 500)
//                .retryOnException(e -> e instanceof WebServiceException)
//                .retryExceptions(IOException.class, TimeoutException.class)
//                .ignoreExceptions(BusinessException.class, OtherBusinessException.class)
//                .failAfterMaxAttempts(true)
                .build();

        // 3. 统一Registry创建，或者自定义配置创建
        RetryRegistry registry = RetryRegistry.of(config);
        Retry retryWithDefaultConfig = registry.retry("name1");
        RetryConfig custom = RetryConfig.custom()
                .waitDuration(Duration.ofMillis(100))
                .build();
        Retry retryWithCustomConfig = registry.retry("name2", custom);
    }
}
