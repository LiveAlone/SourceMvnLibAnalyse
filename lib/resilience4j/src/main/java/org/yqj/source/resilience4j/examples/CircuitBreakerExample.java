package org.yqj.source.resilience4j.examples;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.core.SupplierUtils;
import io.github.resilience4j.core.functions.CheckedSupplier;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/19
 */
@Slf4j
public class CircuitBreakerExample {
    public static void main(String[] args) throws Exception {
//        base();

//        eventListener();

        concurrentTest();
    }

    public static void concurrentTest() throws Exception {
        MockRpcCall mockRpcCall = new MockRpcCall();
        mockRpcCall.setFailRate(10);

        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(10)
                .minimumNumberOfCalls(1)
                .permittedNumberOfCallsInHalfOpenState(5)
                .waitDurationInOpenState(Duration.ofMillis(10000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
                .slidingWindowSize(10)
                .build();

        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("localTest");
        while (true) {
            Try.ofSupplier(circuitBreaker.decorateSupplier(mockRpcCall))
                    .onSuccess(s -> log.info("rpc call success, result is :{}", s))
                    .onFailure(e-> log.info("rpc call error", e));
            Thread.sleep(500);
        }
    }

    public static void eventListener() {
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
        circuitBreakerRegistry.getEventPublisher()
                .onEntryAdded(entryAddedEvent -> {
                    CircuitBreaker addedCircuitBreaker = entryAddedEvent.getAddedEntry();
                    log.info("CircuitBreaker {} added", addedCircuitBreaker.getName());
                })
                .onEntryRemoved(entryRemovedEvent -> {
                    CircuitBreaker removedCircuitBreaker = entryRemovedEvent.getRemovedEntry();
                    log.info("CircuitBreaker {} removed", removedCircuitBreaker.getName());
                });


        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("testLocal");
        circuitBreaker.getEventPublisher().onEvent(event -> log.info("gain event info content is :{}", event));
        Supplier<String> checkedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "localSupplyContent";
        });
        Try<String> ts = Try.ofSupplier(checkedSupplier);
        System.out.println(ts.get());
    }

    public static void base() {
        // 1. 创建熔断配置 CircuitBreakerConfig
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .permittedNumberOfCallsInHalfOpenState(2)
                .slidingWindowSize(2)
                .recordExceptions(IOException.class, TimeoutException.class)
//                .ignoreExceptions(BusinessException.class, OtherBusinessException.class)
                .build();

        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("name_test");

        // 2. 通过decorate 修饰执行
//        Supplier<String> decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, () -> "this is test supply");
//        String result = Try.ofSupplier(decoratedSupplier).recover(throwable -> "Hello from Recovery").get();

        // 3. 直接执行
//        String result = circuitBreaker.executeSupplier(() -> "this is test 2 supply");
//        System.out.println(result);

        // 4. 异常状态恢复
//        Supplier<String> checkedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, () -> {
//                    throw new RuntimeException("BAM!");
//                });
//        Try<String> result = Try.ofSupplier(checkedSupplier).recover(throwable -> "Hello Recovery");
//        System.out.println(result.isSuccess());
//        System.out.println(result.get());

        // 5. 错误恢复， circuitBreaker 不会记录错误消息
//        Supplier<String> supplier = () -> {
//            throw new RuntimeException("BAM!");
//        };
//        Supplier<String> supplierWithRecovery = SupplierUtils.recover(supplier, (exception) -> "Hello Recovery");
//        String result = circuitBreaker.executeSupplier(supplierWithRecovery);
//        System.out.println(result);

        // 6. supplier 依赖执行
//        Supplier<String> supplier = () -> {
//            throw new RuntimeException("BAM!");
//        };
//        Supplier<String> supplierWithResultAndExceptionHandler = SupplierUtils.andThen(supplier, (result, exception) -> "Hello Recovery");
//        String result = circuitBreaker.executeSupplier(supplierWithResultAndExceptionHandler);
//        System.out.println(result);

        // 7 circuit breaker 状态转换
//        circuitBreaker.transitionToDisabledState();
//        circuitBreaker.transitionToClosedState(); // will transition to CLOSED state and re-enable normal behaviour, keeping metrics
//        circuitBreaker.transitionToForcedOpenState();
//        circuitBreaker.reset();

        // 8. 自定义熔断器存储
//        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.custom()
//                .withRegistryStore(new CacheCircuitBreakerRegistryStore())
//                .build();
    }
}
