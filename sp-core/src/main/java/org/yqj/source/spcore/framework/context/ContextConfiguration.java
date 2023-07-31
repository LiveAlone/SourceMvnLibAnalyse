package org.yqj.source.spcore.framework.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/12
 * Email: yaoqijunmail@foxmail.com
 */
@Configuration
@Slf4j
public class ContextConfiguration {

    @Bean(name = {"a", "b", "c", "d"})
    public ContextPerson personYao() {
        ContextPerson p = new ContextPerson("1", "yao", 10);
        log.info("person yao is :{}, {}", p, System.identityHashCode(p));
        return p;
    }

    @Bean
    public ContextPerson personQi() {
        ContextPerson p = new ContextPerson("2", "qi", 20);
        log.info("person qi is :{}, {}", p, System.identityHashCode(p));
        return p;
    }

    @Bean
    public ContextPerson personJun() {
        ContextPerson p = new ContextPerson("3", "jun", 30);
        log.info("person jun is :{}, {}", p, System.identityHashCode(p));
        return p;
    }

    @Bean
    public Map<String, ContextPerson> contextPersonHoldeMap(){
        Map<String, ContextPerson> mp = new HashMap<>();
        mp.put("yao", personYao());
        mp.put("qi", personQi());
        mp.put("jun", personJun());
        mp.forEach((k, v) -> log.info("key is :{}, value is :{}, hashcode is :{}", k, v, System.identityHashCode(v)));
        return mp;
    }
}
