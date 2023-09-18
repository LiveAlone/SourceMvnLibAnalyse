package org.yqj.source.resilience4j.examplesold;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Date;
import java.util.function.Supplier;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/10/14
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class RateLimiterExample {

    public static void main(String[] args) throws Exception {
//        base();
        testBase();
    }

    public static void testBase() throws InterruptedException {
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
//            Try.ofSupplier(restrictedCall)
//                    .onSuccess(s -> log.info("success execute :{}", s))
//                    .onFailure(e -> log.error("fail :{}", e));
//            Thread.sleep(80);
        }
    }

    public static void base() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofMillis(1))
                .limitForPeriod(10)
                .timeoutDuration(Duration.ofMillis(25))
                .build();

        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);

        RateLimiter rateLimiterWithDefaultConfig = rateLimiterRegistry.rateLimiter("name1");

        RateLimiter rateLimiterWithCustomConfig = rateLimiterRegistry.rateLimiter("name2", config);

        Supplier<String> restrictedCall = RateLimiter.decorateSupplier(rateLimiterWithDefaultConfig, () -> "SUCCESS");

        Try.ofSupplier(restrictedCall)
                .onSuccess(System.out::println)
                .onFailure(e -> log.error("fail :{}", e));
    }
}
