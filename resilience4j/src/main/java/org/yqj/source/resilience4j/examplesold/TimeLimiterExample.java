package org.yqj.source.resilience4j.examplesold;

import io.github.resilience4j.timelimiter.TimeLimiter;

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
public class TimeLimiterExample {

    public static void main(String[] args) throws Exception {
        TimeLimiter timeLimiter = TimeLimiter.of(Duration.ofMillis(200));
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        CompletionStage<String> result =
        timeLimiter.executeCompletionStage(scheduledExecutorService, ()->CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            return "test";
        }));

        result.whenComplete((s, e) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("test result is " + s);
        });

        // common-pool-worker-3 执行， 可以指定线程池
//        String result = timeLimiter.executeFutureSupplier(() -> CompletableFuture.supplyAsync(()->{
//            System.out.println(Thread.currentThread().getName());
//            return "test";
//        }));
//        System.out.println(Thread.currentThread().getName());
//        System.out.println("success");
    }


}
