package org.yqj.source.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.yqj.source.webflux.domain.Person;
import org.yqj.source.webflux.webflux.EchoHandler;
import reactor.core.publisher.Mono;

/**
 * @author yaoqijun on 2017-11-05.
 */
@RestController
@Slf4j
public class WelcomeController {

    @Autowired
    private EchoHandler echoHandler;


    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String indexPage(){
        log.info("current thread is :{}", Thread.currentThread().getName());
        return "this is test index paging info";
    }

    @Bean
    public RouterFunction<ServerResponse> monoEchoFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/echo"),
                request -> ServerResponse.ok().body(Mono.just(Thread.currentThread().getName()), String.class));
    }

    @Bean
    public RouterFunction<ServerResponse> monoPersonFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/person/{id}"), request ->
                ServerResponse.ok().body(echoHandler.queryById(Integer.valueOf(request.pathVariable("id"))), Person.class)
        );
    }
}
