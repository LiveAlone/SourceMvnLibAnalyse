package org.yqj.source.spcache;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;
import org.yqj.source.spcache.entiry.CachePerson;
import org.yqj.source.spcache.entiry.PersonService;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/6
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class RunCommandLine implements CommandLineRunner {

    @Resource
    private PersonService personService;

    @Resource
    private CacheManager cacheManager;

    @Override
    public void run(String... args) throws Exception {
        log.info("starting command line ...");

        for (int i = 0; i < 10; i++) {
            int value = i % 2;
            CachePerson person = personService.gainCachePerson(value, value + 18);
            log.info("command line get cache person is :{}", person);
        }

        printCacheManager();
    }

    private void printCacheManager() {
        // 内存缓存
        Cache cache = cacheManager.getCache(PersonService.CACHE_PERSON);
        if (cache instanceof ConcurrentMapCache) {
            ConcurrentMapCache concurrentMapCache = (ConcurrentMapCache) cache;
            log.info("cache name is :{}", concurrentMapCache.getName());
            log.info("cache native cache is :{}", concurrentMapCache.getNativeCache());
        }

//        if (cache instanceof CaffeineCache) {
//            CaffeineCache caffeineCache = (CaffeineCache) cache;
//            log.info("cache name is :{}", caffeineCache.getName());
//            log.info("cache native cache is :{}", caffeineCache.getNativeCache());
//        }
    }
}
