package org.yqj.source.spcache;

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
public class CacheConfiguration {

//    @Bean
//    public RedisCacheConfiguration redisCacheConfiguration() {
//        // 默认全局配置, 自动创建CacheName
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(10)).disableCachingNullValues()
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

    @Bean
    public CacheManager localCacheManager() {
        return new ConcurrentMapCacheManager();
    }
}
