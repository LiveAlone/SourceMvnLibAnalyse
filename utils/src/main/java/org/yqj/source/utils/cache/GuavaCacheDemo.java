package org.yqj.source.utils.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
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
        Cache<String, String> graphs = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
        graphs.put("k1", "v1");
        log.info("k1 value is {}", graphs.getIfPresent("k1"));

//        CacheLoadDemo();

//        CallableDemo();
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
