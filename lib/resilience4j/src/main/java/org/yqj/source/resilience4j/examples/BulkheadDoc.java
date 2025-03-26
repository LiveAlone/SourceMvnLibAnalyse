package org.yqj.source.resilience4j.examples;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.bulkhead.ThreadPoolBulkhead;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadConfig;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadRegistry;
import io.github.resilience4j.core.functions.CheckedSupplier;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CompletionStage;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/19
 */
@Slf4j
public class BulkheadDoc {

    public static void main(String[] args) throws Exception {
        decorate();
    }

    public static void listener() throws Exception {
        // 1. 添加注册中心监听器
        BulkheadRegistry registry = BulkheadRegistry.ofDefaults();
        registry.getEventPublisher()
                .onEntryAdded(entryAddedEvent -> {
                    Bulkhead addedBulkhead = entryAddedEvent.getAddedEntry();
                    log.info("Bulkhead {} added", addedBulkhead.getName());
                })
                .onEntryRemoved(entryRemovedEvent -> {
                    Bulkhead removedBulkhead = entryRemovedEvent.getRemovedEntry();
                    log.info("Bulkhead {} removed", removedBulkhead.getName());
                });

        // 2. 添加隔板监听器
        Bulkhead bulkhead = Bulkhead.ofDefaults("name");
        bulkhead.getEventPublisher()
                .onCallPermitted(event -> log.info(""))
                .onCallRejected(event -> log.info(""))
                .onCallFinished(event -> log.info(""));
    }

    public static void decorate() throws Exception {
        // 1. decorate function
//        Bulkhead bulkhead = Bulkhead.ofDefaults("name");
//        CheckedSupplier<String> decoratedSupplier = Bulkhead.decorateCheckedSupplier(bulkhead, () -> "This can be any method which returns: 'Hello");
//        Try<String> result = Try.of(decoratedSupplier::get).map(value -> value + " world'");
//        System.out.println(result.isSuccess());
//        System.out.println(result.get());

        // 2. ThreadPool
        ThreadPoolBulkheadConfig config = ThreadPoolBulkheadConfig.custom()
                .maxThreadPoolSize(10)
                .coreThreadPoolSize(2)
                .queueCapacity(20)
                .build();

        ThreadPoolBulkhead bulkhead = ThreadPoolBulkhead.of("name", config);
        CompletionStage<String> supplier = bulkhead.executeSupplier(() -> "Hello World");
        supplier.thenAccept(System.out::println);
        bulkhead.close();
    }

    public static void create() {

        // 1. 创建基于内存管理隔板
        BulkheadRegistry bulkheadRegistry = BulkheadRegistry.ofDefaults();

        // 2. 配置创建方式
//        BulkheadConfig config = BulkheadConfig.custom()
//                .maxConcurrentCalls(150)
//                .maxWaitDuration(Duration.ofMillis(500))
//                .build();
//
//        BulkheadRegistry registry = BulkheadRegistry.of(config);
//        Bulkhead bulkheadWithDefaultConfig = registry.bulkhead("name1");
//        // 熔断类似支持自定义配置
////        Bulkhead bulkheadWithCustomConfig = registry.bulkhead("name2", custom);

        // 3. ThreadPool 创建方式, 全局配置和自定义配置方式
        ThreadPoolBulkheadConfig config = ThreadPoolBulkheadConfig.custom()
                .maxThreadPoolSize(10)
                .coreThreadPoolSize(2)
                .queueCapacity(20)
                .build();

        ThreadPoolBulkheadRegistry registry = ThreadPoolBulkheadRegistry.of(config);

        ThreadPoolBulkhead bulkheadWithDefaultConfig = registry.bulkhead("name1");

        ThreadPoolBulkheadConfig custom = ThreadPoolBulkheadConfig.custom()
                .maxThreadPoolSize(5)
                .build();
        ThreadPoolBulkhead bulkheadWithCustomConfig = registry.bulkhead("name2", custom);
    }
}
