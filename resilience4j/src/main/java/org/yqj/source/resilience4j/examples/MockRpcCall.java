package org.yqj.source.resilience4j.examples;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/19
 */
@Data
@Slf4j
public class MockRpcCall implements Supplier<String> {

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
            log.info("thread interrupted exception: ", e);
            throw new RuntimeException(e.getCause());
        }
        return "SUCCESS";
    }
}
