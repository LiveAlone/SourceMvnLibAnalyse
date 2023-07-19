package org.yqj.source.utils.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import com.google.common.graph.Graph;
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

//        CallableDemo();

        PutDemo();
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

    private static void PutDemo() {
        Cache<String, String> graphs = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
        graphs.put("k1", "v1");
        log.info("k1 value is {}", graphs.getIfPresent("k1"));
    }

    private static void CallableDemo() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build();
        try {
            String result = cache.get("test", () -> String.format("%s-Callable", "test"));
            log.info("result is {}", result);
        } catch (ExecutionException e) {
            log.error("get cache error", e);
        }
    }

    private static void CacheLoadDemo() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, String>() {
                            @Override
                            public String load(String key) throws Exception {
                                return String.format("%s-Cache-Loader", key);
                            }
                        });
        try {
            String result = cache.get("test");
            log.info("result is {}", result);
        } catch (ExecutionException e) {
            log.error("get cache error", e);
        }
    }
}
