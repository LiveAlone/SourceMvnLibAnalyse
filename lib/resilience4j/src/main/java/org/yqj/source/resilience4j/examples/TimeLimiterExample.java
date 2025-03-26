package org.yqj.source.resilience4j.examples;

import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2021/3/10
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class TimeLimiterExample {

    public static void main(String[] args) throws Exception {
//        TimeLimiter timeLimiter = TimeLimiter.of(Duration.ofMillis(200));
//
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
//        CompletionStage<String> result = timeLimiter.executeCompletionStage(scheduledExecutorService, ()->CompletableFuture.supplyAsync(()->{
//            System.out.println(Thread.currentThread().getName());
//            return "test";
//        }));
//
//        result.whenComplete((s, e) -> {
//            System.out.println(Thread.currentThread().getName());
//            System.out.println("test result is " + s);
//        });

        // common-pool-worker-3 执行， 可以指定线程池
//        String result = timeLimiter.executeFutureSupplier(() -> CompletableFuture.supplyAsync(()->{
//            System.out.println(Thread.currentThread().getName());
//            return "test";
//        }));
//        System.out.println(Thread.currentThread().getName());
//        System.out.println("success");

        decorate();
    }

    public static void decorate() throws Exception {
        TimeLimiter timeLimiter = TimeLimiter.of(Duration.ofMillis(100));

        // 1. 执行效率
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
//        CompletionStage<String> result = timeLimiter.executeCompletionStage(scheduledExecutorService, () -> CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread().getName());
//            return "test";
//        }));
//        result.thenAccept(s -> log.info("success result is {}", s))
//                .exceptionally(e -> {
//                    log.error("exception is ", e);
//                    return null;
//                });
//        scheduledExecutorService.shutdown();

        String result = timeLimiter.executeFutureSupplier(() -> CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "test";
        }));
        log.info("success result is {}", result);
    }

    public static void create() {
//        TimeLimiterRegistry timeLimiterRegistry = TimeLimiterRegistry.ofDefaults();

        // 2. 配置方式创建
//        TimeLimiterConfig config = TimeLimiterConfig.custom()
//                .cancelRunningFuture(true)
//                .timeoutDuration(Duration.ofMillis(500))
//                .build();
//
//        TimeLimiterRegistry timeLimiterRegistry = TimeLimiterRegistry.of(config);
//        TimeLimiter timeLimiterWithDefaultConfig = timeLimiterRegistry.timeLimiter("name1");

        // 3. 配置方式创建
//        TimeLimiterConfig config = TimeLimiterConfig.custom()
//                .cancelRunningFuture(false)
//                .timeoutDuration(Duration.ofMillis(1000))
//                .build();
//
//        TimeLimiter timeLimiterWithCustomConfig = registry.timeLimiter("name2", config);
    }

}
