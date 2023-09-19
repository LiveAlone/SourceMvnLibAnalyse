package org.yqj.source.resilience4j.examples;

import io.github.resilience4j.core.functions.CheckedRunnable;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Date;
import java.util.function.Supplier;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/19
 */
@Slf4j
public class RateLimiterDoc {
    public static void main(String[] args) throws Exception {
//        decorate();

        testBase();
    }

    public static void testBase() throws Exception {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(10)
                .timeoutDuration(Duration.ZERO)
                .build();
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);
        RateLimiter rateLimiterWithDefaultConfig = rateLimiterRegistry.rateLimiter("localRateLimit");
        Supplier<String> restrictedCall = RateLimiter.decorateSupplier(rateLimiterWithDefaultConfig, () -> "SUCCESS");

        while (true) {
            log.info("current time stamp :{}", new Date());
            Try.ofSupplier(restrictedCall)
                    .onSuccess(s -> log.info("success execute :{}", s))
                    .onFailure(e -> log.error("fail: ", e));
            Thread.sleep(80);
        }
    }

    public static void events() {
        // 1. 事件监听器
        RateLimiterRegistry registry = RateLimiterRegistry.ofDefaults();
        registry.getEventPublisher()
                .onEntryAdded(entryAddedEvent -> {
                    RateLimiter addedRateLimiter = entryAddedEvent.getAddedEntry();
                    log.info("RateLimiter {} added", addedRateLimiter.getName());
                })
                .onEntryRemoved(entryRemovedEvent -> {
                    RateLimiter removedRateLimiter = entryRemovedEvent.getRemovedEntry();
                    log.info("RateLimiter {} removed", removedRateLimiter.getName());
                });

        RateLimiter rateLimiter = registry.rateLimiter("backend");
        rateLimiter.getEventPublisher()
                .onSuccess(event -> log.info(""))
                .onFailure(event -> log.info(""));

        // 2. 持久
//        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.custom()
//                .withRegistryStore(new CacheRateLimiterRegistryStore())
//                .build();
    }

    public static void decorate() {
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.ofDefaults();
        RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter("local");
        Supplier<String> result = RateLimiter.decorateSupplier(rateLimiter, () -> "this is rate limit");

        String content = Try.ofSupplier(result).onFailure(e -> log.info("error message is {}", e.getMessage())).get();
        log.info("result is {}", content);

        // ! 可配置动态修改rate limit的配置
        rateLimiter.changeLimitForPeriod(100);
    }

    public static void create() {
//        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.ofDefaults();

        // 2. 设置没毫秒10个请求, 等待时间最多25毫秒
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofMillis(1))
                .limitForPeriod(10)
                .timeoutDuration(Duration.ofMillis(25))
                .build();

        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);
        RateLimiter rateLimiterWithDefaultConfig = rateLimiterRegistry.rateLimiter("name1");
        RateLimiter rateLimiterWithCustomConfig = rateLimiterRegistry.rateLimiter("name2", config);
    }
}
