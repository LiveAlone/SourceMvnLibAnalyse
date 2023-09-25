package org.yqj.source.utils.guava;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

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
        RateLimiter rateLimiter = RateLimiter.create(0.1);
        while (true){
            rateLimiter.acquire();
            log.info("rate limit demo");
        }
    }
}
