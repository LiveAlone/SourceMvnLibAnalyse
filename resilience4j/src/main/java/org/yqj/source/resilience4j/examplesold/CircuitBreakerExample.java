package org.yqj.source.resilience4j.examplesold;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.core.SupplierUtils;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/10/13
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class CircuitBreakerExample {

    public static void main(String[] args) throws Exception {
        base();
//        functionExecution();
//        eventListener();
        singleTest();
    }

    public static void singleTest() throws InterruptedException {
        MockRpcCall mockRpcCall = new MockRpcCall();

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
//            Try.ofSupplier(circuitBreaker.decorateSupplier(mockRpcCall))
//                    .onSuccess(System.out::println)
//                    .onFailure(e->{
//                        System.out.println(e);
//                    });
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
//        Try<String> ts = Try.of(CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> {
//            Thread.sleep(200);
//            return "localSupplyContentHa";
//        }));
//        System.out.println(ts.get());
    }

    public static void functionExecution() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("localTest");
//        Try<String> ts = Try.of(CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "HelloTest"));
//        System.out.println(ts.get());

        try {
            String result = circuitBreaker.executeCheckedSupplier(() -> "hello");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        Supplier<String> supplier = () -> {
            throw new RuntimeException("BAM!");
        };

        Supplier<String> supplierWithRecovery = SupplierUtils.recover(supplier, (exception) -> "Hello Recovery");
        String result = circuitBreaker.executeSupplier(supplierWithRecovery);
    }

    public static void circuitBreaker() {
        // 自定义
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .recordExceptions(IOException.class, TimeoutException.class)
                .build();

        CircuitBreaker customCircuitBreaker = CircuitBreaker
                .of("testName", circuitBreakerConfig);

//        io.vavr.collection.Map<String, String> circuitBreakerTags = io.vavr.collection.HashMap
//                .of("key1", "value1", "key2", "value2");
//
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.custom()
                .withCircuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .addRegistryEventConsumer(new RegistryEventConsumer() {
                    @Override
                    public void onEntryAddedEvent(EntryAddedEvent entryAddedEvent) {

                    }

                    @Override
                    public void onEntryRemovedEvent(EntryRemovedEvent entryRemoveEvent) {

                    }

                    @Override
                    public void onEntryReplacedEvent(EntryReplacedEvent entryReplacedEvent) {

                    }
                })
//                .withTags(circuitBreakerTags)
                .build();
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("testName");

    }

    public static void config() {

        // 添加自定义熔断配置方式
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//                .failureRateThreshold(70)
//                .build();
//
//        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
//        circuitBreakerRegistry.addConfiguration("someSharedConfig", circuitBreakerConfig);
//
//        CircuitBreaker circuitBreaker = circuitBreakerRegistry
//                .circuitBreaker("name", "someSharedConfig");

        // 已有配置基础上重新定义配置方式, 由于构建熔断配置
//        CircuitBreakerConfig defaultConfig = circuitBreakerRegistry
//                .getDefaultConfig();
//
//        CircuitBreakerConfig overwrittenConfig = CircuitBreakerConfig
//                .from(defaultConfig)
//                .waitDurationInOpenState(Duration.ofSeconds(20))
//                .build();
    }

    public static void base() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .minimumNumberOfCalls(10)
                .permittedNumberOfCallsInHalfOpenState(5)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slowCallRateThreshold(50)
                .slowCallDurationThreshold(Duration.ofMillis(2000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
                .slidingWindowSize(10)
                .recordExceptions(IOException.class, TimeoutException.class)
                .build();

        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(config);

        // circuit break 定制不同的 config 配置
        CircuitBreaker circuitBreakerWithCustomConfig = circuitBreakerRegistry.circuitBreaker("name2", config);
        CircuitBreaker circuitBreakerWithDefaultConfig = circuitBreakerRegistry.circuitBreaker("name1");
    }

    /**
     * Description:
     *
     * @author yaoqijun
     * @date 2020/10/14
     * Email: yaoqijunmail@foxmail.com
     */
    @Data
    public static class MockRpcCall implements Supplier<String> {

        private int failRate = 50;

        private long baseDelay = 200;

        private long delayRandom = 200;


        @Override
        public String get() {
            if (ThreadLocalRandom.current().nextInt(100) < failRate){
                throw new RuntimeException("rpcCallError");
            }
            try {
                Thread.sleep(baseDelay + ThreadLocalRandom.current().nextLong(delayRandom));
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getCause());
            }
            return "SUCCESS";
        }
    }
}
