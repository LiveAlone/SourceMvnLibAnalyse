package org.yqj.source.webflux.webflux;

import org.yqj.source.webflux.domain.Person;
import reactor.core.publisher.Mono;

/**
 * @author yaoqijun on 2017-11-05.
 */
public interface EchoHandler {
    Mono<Person> queryById(Integer id);
}
