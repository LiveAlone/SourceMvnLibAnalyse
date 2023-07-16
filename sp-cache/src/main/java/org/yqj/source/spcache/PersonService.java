package org.yqj.source.spcache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

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

    @Cacheable(value = "itemCache")
    public CachePerson gainCachePerson(long id) {
        log.info("******** gain cache person without cache");
        return new CachePerson(id, "test", 10);
    }
}
