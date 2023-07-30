package org.yqj.source.spcache.entiry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.yqj.source.spcache.entiry.CachePerson;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/4
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class PersonService {

    public static final String CACHE_PERSON = "gainCachePerson";

    @Cacheable(value = CACHE_PERSON)
    public CachePerson gainCachePerson(long id, int age) {
        log.info("******** gain cache person without cache");
        return new CachePerson(id, "test", age);
    }
}
