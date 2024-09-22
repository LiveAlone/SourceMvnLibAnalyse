package org.yqj.source.common.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import com.google.common.graph.Graph;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.io.StringReader;
import java.security.Key;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/7/18
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class GuavaCacheDemo {
    public static void main(String[] args) throws Exception {
//        CacheLoadDemo();

//        CallableExpireDemo();

//        PutDemo();

        expireRefreshWrite();

    }

    private static void expireRefreshWrite() throws Exception {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(16)
//                .expireAfterAccess(1, TimeUnit.SECONDS)
//                .expireAfterWrite(1, TimeUnit.SECONDS)
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        Thread.sleep(5000);
                        return String.format("%s-Cache-Loader", key);
                    }

                    @Override
                    public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                        // 线程异步方式load
                        return super.reload(key, oldValue);
                    }
                });
        cache.put("expire", "expire");
        Thread.sleep(2000);
        int ct = 10;
        for (int i = 0; i < ct; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String value = cache.get("expire");
                        log.info("thread:{} value is {}", Thread.currentThread().getName(), value);
                    } catch (Exception e) {
                        log.error("get cache error", e);
                    }
                }
            }).start();
        }
        Thread.sleep(10000);
        String content = cache.get("expire");
        log.info("content is {}", content);
    }

    private static void EvictionStrategy() {
        // Size
//        Cache<String, String> graphs = CacheBuilder.newBuilder()
//                .maximumSize(1000) // 最大Size设定
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .build();

        // 通过Key-Value 权重驱逐
//        Cache<String, String> graphs = CacheBuilder.newBuilder()
//                .maximumWeight(1000)
//                .weigher((Weigher<String, String>) (key, value) -> value.length())
//                .build();

        Cache<String, String> graphs = CacheBuilder.newBuilder()
//                .expireAfterWrite(10, TimeUnit.MINUTES)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    private static void CallableExpireDemo() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(16)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build();
        cache.put("expire", "expire");
        try {
            Thread.sleep(2000);
            String result = cache.get("expire", () -> String.format("%s-Callable", "expire"));
            log.info("result is {}", result);
        } catch (Exception e) {
            log.error("get cache error", e);
        }
    }

    private static void CacheLoadDemo() {
        LoadingCache<String, String> demoCache = CacheBuilder.newBuilder()
                .removalListener(notification -> {
                    log.info("remove key is {}, value is {}", notification.getKey(), notification.getValue());
                })
                .maximumSize(16)
                .build(
                        new CacheLoader<String, String>() {
                            @Override
                            public String load(String key) throws Exception {
                                return String.format("%s-Cache-Loader", key);
                            }
                        });
        for (int i = 0; i < 20; i++) {
            demoCache.put(String.valueOf(i), String.valueOf(i));
        }
        demoCache.put("123", "456");
    }
}
