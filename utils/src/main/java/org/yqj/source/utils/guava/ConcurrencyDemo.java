package org.yqj.source.empty.guava;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/7/20
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class ConcurrencyDemo {
    public static void main(String[] args) {
        ListenableFutureDemo();
    }

    private static void ListenableFutureDemo() {
        // 修饰ExecuteService
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> explosion = service.submit(
                new Callable<String>() {
                    public String call() {
                        return "hello world";
                    }
                });

        Futures.addCallback(
                explosion,
                new FutureCallback<String>() {
                    // we want this handler to run immediately after we push the big red button!
                    public void onSuccess(String explosion) {
                        log.info("success");
                    }
                    public void onFailure(Throwable thrown) {
                        log.info("failure");
                    }
                },
                service);
    }
}
