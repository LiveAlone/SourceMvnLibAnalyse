package org.yqj.source.resilience4j.source;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/25
 */
@Slf4j
public class RateLimiterSource {
    public static void main(String[] args) {
        decorate();
    }

    public static void decorate() {
        RateLimiter rateLimiter = create();
        Supplier<String> func = () -> "this is rate limit";
        while (true) {
            String content = Try.ofSupplier(RateLimiter.decorateSupplier(rateLimiter, func))
                    .onFailure(e -> log.info("error message is {}", e.getMessage()))
                    .get();
            log.info("result is {}", content);
        }
    }

    public static void loopAcquire() {
        RateLimiter rateLimiter = create();
        while (true){
            boolean success = rateLimiter.acquirePermission();
            log.info("success acquire permission :{}", success);
        }
    }

    public static RateLimiter create() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(1)
                .timeoutDuration(Duration.ofSeconds(1))
                .build();
        return RateLimiter.of("source", config);
    }
}
