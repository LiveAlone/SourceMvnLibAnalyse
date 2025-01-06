package org.yqj.source.webflux.webflux;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.yqj.source.webflux.domain.Person;
import reactor.core.publisher.Mono;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2018/8/15
 * Email: yaoqijunmail@foxmail.com
 */
@Component
public class EchoHandlerImpl implements EchoHandler{

    public Mono<ServerResponse> echo(ServerRequest request) {
        return ServerResponse.ok().body(request.bodyToMono(String.class), String.class);
    }

    @Override
    public Mono<Person> queryById(Integer id){
        return Mono.just(new Person(id, String.valueOf(id) + "-name HaHa", 18));
    }
}
