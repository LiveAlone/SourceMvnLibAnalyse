package org.yqj.source.resilience4j.examples;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerEvent;
import io.github.resilience4j.consumer.CircularEventConsumer;
import io.github.resilience4j.core.functions.CheckedSupplier;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/9/18
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class CircuitBreakerDoc {

    public static void main(String[] args) {
        decorate();
    }

    public static void registryEvents() {
        // 1. 监听事件
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

        // 处理器添加监听事件，监听消息
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("none");
        circuitBreaker.getEventPublisher()
                .onSuccess(event -> log.info(""))
                .onError(event -> log.info(""))
                .onIgnoredError(event -> log.info(""))
                .onReset(event -> log.info(""))
                .onStateTransition(event -> log.info(""));
        circuitBreaker.getEventPublisher()
                .onEvent(event -> log.info(""));

        // 循环队列，监听熔断时间消息
        CircularEventConsumer<CircuitBreakerEvent> ringBuffer = new CircularEventConsumer<>(10);
        circuitBreaker.getEventPublisher().onEvent(ringBuffer);
        List<CircuitBreakerEvent> bufferedEvents = ringBuffer.getBufferedEvents();

        // 监控事件支持持久化
//        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.custom()
//                .withRegistryStore(new CacheCircuitBreakerRegistryStore())
//                .build();
    }

    /**
     * 修饰执行熔断器
     */
    public static void decorate() {
        // 1. 消费初始化
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");

        // When I decorate my function
        CheckedSupplier<String> decoratedSupplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "This can be any method which returns: 'Hello");

        Try<String> result = Try.of(decoratedSupplier::get)
                .map(value -> value + " world'");

        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    /**
     * 熔断器配置管理
     */
    public static void config() {
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();

        // 1. 构建自定义Config 配置
//        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
//                .failureRateThreshold(70).build();
//
//        // 2. 配置添加到熔断中心， 提供创建使用
//        circuitBreakerRegistry.addConfiguration("someSharedConfig", config);
//        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("name", "someSharedConfig");

        // 3. 基于已有配置添加做出修改方式
        CircuitBreakerConfig defaultConfig = circuitBreakerRegistry.getDefaultConfig();
        CircuitBreakerConfig overwrittenConfig = CircuitBreakerConfig
                .from(defaultConfig)
                .waitDurationInOpenState(Duration.ofSeconds(20))
                .build();
    }

    /**
     * 创建熔断器方式
     */
    public static void createRegistry() {
        // 1. 默认创建熔断中心
//        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();

        // 2. 创建配置方式
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//                .failureRateThreshold(50)
//                .slowCallRateThreshold(50)
//                .waitDurationInOpenState(Duration.ofMillis(1000))
//                .slowCallDurationThreshold(Duration.ofSeconds(2))
//                .permittedNumberOfCallsInHalfOpenState(3)
//                .minimumNumberOfCalls(10)
//                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
//                .slidingWindowSize(60)
////                .recordException(e -> INTERNAL_SERVER_ERROR.equals(getResponse().getStatus()))
//                .recordExceptions(IOException.class, TimeoutException.class)
////                .ignoreExceptions(BusinessException.class, OtherBusinessException.class)
//                .build();
//
//        // 3. 基于配置创建熔断器
//        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
//        // 4. 全局配置创建熔断器
//        CircuitBreaker circuitBreakerWithDefaultConfig = circuitBreakerRegistry.circuitBreaker("name1");
//        // 5. 指定配置创建熔断器, 当然还是那个全局配置
//        CircuitBreaker circuitBreakerWithCustomConfig = circuitBreakerRegistry.circuitBreaker("name2", circuitBreakerConfig);
//
//        // 6. 直接创建熔断器，不用管理
//        CircuitBreaker customCircuitBreaker = CircuitBreaker.of("testName", circuitBreakerConfig);

        // 7. 注册中心支持tags和事件监听
        Map<String, String> circuitBreakerTags = Map.of("key1", "value1", "key2", "value2");

        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.custom()
                .withCircuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .addRegistryEventConsumer(new RegistryEventConsumer() {
                    @Override
                    public void onEntryAddedEvent(EntryAddedEvent entryAddedEvent) {
                        // implementation
                    }

                    @Override
                    public void onEntryRemovedEvent(EntryRemovedEvent entryRemoveEvent) {
                        // implementation
                    }

                    @Override
                    public void onEntryReplacedEvent(EntryReplacedEvent entryReplacedEvent) {
                        // implementation
                    }
                })
                .withTags(circuitBreakerTags)
                .build();

//        CircuitBreakerRegistry registry = CircuitBreakerRegistry.custom()
//                .withRegistryStore(new YourRegistryStoreImplementation())
//                .withCircuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//                .build();
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("testName");
    }

}
