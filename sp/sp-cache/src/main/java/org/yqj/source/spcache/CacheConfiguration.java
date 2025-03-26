package org.yqj.source.spcache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/4
 * Email: yaoqijunmail@foxmail.com
 */
@Configuration
@Slf4j
public class CacheConfiguration {

//    /**
//     * redis cache 缓存支持方式
//     * @return RedisCacheConfiguration
//     */
//    @Bean
//    public RedisCacheConfiguration redisCacheConfiguration() {
//        // 默认全局配置, 自动创建CacheName
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(10)).
//                disableCachingNullValues()
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//    }

//    @Bean
//    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
//        // 不同 cacheName 定制化配置
//        return (builder) -> builder
//                .withCacheConfiguration("itemCache", defaultRedisCacheConfiguration())
//                .withCacheConfiguration("customerCache", defaultRedisCacheConfiguration())
//                .disableCreateOnMissingCache();
//    }
//
//    private RedisCacheConfiguration defaultRedisCacheConfiguration() {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(10)).disableCachingNullValues()
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//    }


//    @Bean
//    public CacheManager cacheManager(Caffeine caffeine) {
//        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
//        caffeineCacheManager.setCaffeine(caffeine);
//        return caffeineCacheManager;
//    }
//
//    @Bean
//    public Caffeine caffeineConfig() {
//        return Caffeine.newBuilder()
//                .maximumSize(10)
//                .expireAfterWrite(60, TimeUnit.SECONDS)
//                .removalListener(new RemovalListener<Object, Object>() {
//                    @Override
//                    public void onRemoval(@Nullable Object key, @Nullable Object value, RemovalCause cause) {
//                        log.info("remove key is :{}, value is :{}, cause is :{}", key, value, cause);
//                    }
//                });
//    }

    /**
     * 1. 基于Map的本地缓存
     * @return ConcurrentMapCacheManager
     */
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }
}
