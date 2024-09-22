package org.yqj.source.common.guava;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/25
 */
@Slf4j
public class RateLimitDemo {
    public static void main(String[] args) {
        rateLimitDemo();
    }

    public static void rateLimitDemo() {
//        RateLimiter rateLimiter = RateLimiter.create(10, Duration.ofSeconds(5));
        RateLimiter rateLimiter = RateLimiter.create(2);
        while (true){
            rateLimiter.acquire();
            log.info("rate limit demo");
        }
    }
}
