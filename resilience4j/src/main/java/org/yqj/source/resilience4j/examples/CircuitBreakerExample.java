package org.yqj.source.resilience4j.examples;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/9/18
 * Email: yaoqijunmail@foxmail.com
 */
public class CircuitBreakerExample {

    public static void main(String[] args) {
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
    }


    /**
     * 创建熔断器方式
     */
    public static void createRegistry() {
        // 1. 默认创建熔断中心
//        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();

        // 2. 创建配置方式
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .slowCallRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slowCallDurationThreshold(Duration.ofSeconds(2))
                .permittedNumberOfCallsInHalfOpenState(3)
                .minimumNumberOfCalls(10)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
                .slidingWindowSize(60)
//                .recordException(e -> INTERNAL_SERVER_ERROR.equals(getResponse().getStatus()))
                .recordExceptions(IOException.class, TimeoutException.class)
//                .ignoreExceptions(BusinessException.class, OtherBusinessException.class)
                .build();

        // 3. 基于配置创建熔断器
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        // 4. 全局配置创建熔断器
        CircuitBreaker circuitBreakerWithDefaultConfig = circuitBreakerRegistry.circuitBreaker("name1");
        // 5. 指定配置创建熔断器, 当然还是那个全局配置
        CircuitBreaker circuitBreakerWithCustomConfig = circuitBreakerRegistry.circuitBreaker("name2", circuitBreakerConfig);
    }

}
