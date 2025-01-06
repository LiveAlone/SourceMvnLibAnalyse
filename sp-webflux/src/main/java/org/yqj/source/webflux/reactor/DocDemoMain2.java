package org.yqj.source.webflux.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/6/16
 * Email: yaoqijunmail@foxmail.com
 */
public class DocDemoMain2 {

    public static void main(String[] args) {

        windowCondition();

        contextTestCondition();

    }

    private static void contextTestCondition() {
        String key = "message";
//        Flux.just("Hello", "YAO", "QI", "jun")
//                .flatMap( s -> Mono.subscriberContext()
//                        .map( ctx -> s + " " + ctx.get(key)))
//                .subscriberContext(ctx -> ctx.put(key, "World"))
//                .subscribe(System.out::println);

//        Flux.just("Hello", "YAO", "QI", "jun")
//                .subscriberContext(ctx -> ctx.put(key, "World"))
//                .flatMap( s -> Mono.subscriberContext()
//                        .map( ctx -> s + " " + ctx.getOrDefault(key, "NONE")))
//                .subscribe(System.out::println);

//        Flux.just("Hello", "YAO", "QI", "jun")
//                .flatMap( s -> Mono.subscriberContext()
//                        .map( ctx -> s + " " + ctx.get(key)))
//                .subscriberContext(ctx -> ctx.put(key, "Reactor"))
//                .subscriberContext(ctx -> ctx.put(key, "World"))
//                .subscribe(System.out::println);

//        Flux.just("Hello", "YAO", "QI", "jun")
//                .flatMap( s -> Mono.subscriberContext()
//                        .map( ctx -> s + " " + ctx.get(key)))
//                .subscriberContext(ctx -> ctx.put(key, "Reactor"))
//                .flatMap( s -> Mono.subscriberContext()
//                        .map( ctx -> s + " " + ctx.get(key)))
//                .subscriberContext(ctx -> ctx.put(key, "World"))
//                .subscribe(System.out::println);

//        Flux.just("Hello", "YAO", "QI", "jun")
//                .flatMap(s -> Mono.subscriberContext()
//                        .map(ctx -> s + " " + ctx.get(key)))
//                .flatMap(s -> Mono.subscriberContext()
//                        .map(ctx -> s + " " + ctx.get(key)))
//                .subscriberContext(ctx -> ctx.put(key, "World"))
//                .subscribe(System.out::println);
    }

    private static void windowCondition() {

        Flux.range(1, 10)
                .window(5, 3)
                .concatMap(g -> g.defaultIfEmpty(-1))
                .subscribe(System.out::println);

//        Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
//                .windowWhile(i -> i % 2 == 0)
//                .concatMap(g -> g.defaultIfEmpty(-1))
//                .subscribe(System.out::println);
    }
}
