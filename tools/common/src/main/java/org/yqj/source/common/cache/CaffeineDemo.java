package org.yqj.source.common.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/7/18
 * Email: yaoqijunmail@foxmail.com
 */
public class CaffeineDemo {
    public static void main(String[] args) {
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
    }
}
