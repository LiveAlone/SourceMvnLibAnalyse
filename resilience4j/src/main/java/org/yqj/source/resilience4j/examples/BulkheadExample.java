package org.yqj.source.resilience4j.examples;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.bulkhead.ThreadPoolBulkheadRegistry;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/10/14
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class BulkheadExample {

    public static void main(String[] args) throws InterruptedException {
        configExecution();

//        threadInterrupt();
    }

    public static void threadInterrupt() throws InterruptedException {
//       Thread thread = new Thread(()->{
//           log.info("test thread started");
//           log.info("is current thread interrupt action :{} ", Thread.currentThread().isInterrupted());
//
//           Thread.currentThread().interrupt();
//           log.info("is current thread interrupt action :{} ", Thread.currentThread().isInterrupted());
//
//       });
//       thread.start();

        Thread thread = new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1000);
                    log.info("current thread is interrupted :{}", Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
        thread.start();
        Thread.sleep(10000);
//        thread.interrupt();
        System.out.println(Thread.interrupted());
    }

    public static void configExecution() {
        BulkheadConfig bulkheadConfig = BulkheadConfig.custom()
                .maxConcurrentCalls(2)
                .maxWaitDuration(Duration.ofMillis(100))
                .build();
        Bulkhead bulkhead = BulkheadRegistry.of(bulkheadConfig).bulkhead("localTest");

        Function<String, String> method = s -> {
            try {
                log.info("thread :{} started execute configuration", s);
                Thread.sleep(100 + ThreadLocalRandom.current().nextLong(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "SUCCESS";
        };
        Function<String, String> methodDecorate = Bulkhead.decorateFunction(bulkhead, method);

        for (int i = 0; i < 10; i++) {
            // 两个线程执行成功
            int index = i;
            new Thread(() -> {
                String key = "currentThread" + index;
                while (true) {
                    methodDecorate.apply(key);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
