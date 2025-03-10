package org.yqj.source.webflux.reactor;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.UnicastProcessor;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/6/7
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class DocDemoMain {

    public static void main(String[] args) {

//        subscribeMethodVoid();

//        baseSubscribeOutput();

//        fluxGenerateFunc();

//        fluxCreateConfig();

//        fluxHandleConfig();

//        publishOnTest();

//        subscribeOnTest();

//        errorHandlerCondition();

//        retryHandler();

//        hotColdHandlerTest();

//        testDelayElement();

//        bufferCondition();
//        windowCondition();

//        connectableFluxTest();

//        groupMapping();

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
        // window 窗口化
//        Flux.range(1, 10)
//                .window(5, 3)
//                .concatMap(g -> g.defaultIfEmpty(-1))
//                .subscribe(System.out::println);

//        Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
//                .windowWhile(i -> i % 2 == 0)
//                .concatMap(g -> g.defaultIfEmpty(-1))
//                .subscribe(System.out::println);
    }

    /**
     * group 分组
     */
    private static void groupMapping() {
        Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
                .groupBy(i -> i % 2 == 0 ? "even" : "odd")
                .concatMap(g -> g.defaultIfEmpty(-1).map(String::valueOf).startWith(g.key()))
                .subscribe(System.out::println);
    }


    /**
     * subscribe 监听链接
     */
    private static void connectableFluxTest() {
//        Flux<Integer> source = Flux.range(1, 3)
//                .doOnSubscribe(s -> System.out.println("subscribed to source"));
//
//        ConnectableFlux<Integer> co = source.publish();
//        co.subscribe(System.out::println, e -> {}, () -> {});
//        co.subscribe(System.out::println, e -> {}, () -> {});
//
//        System.out.println("done subscribing");
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("will now connect");
//        co.connect();


//        Flux<Integer> source = Flux.range(1, 3).doOnSubscribe(s -> System.out.println("subscribed to source"));
//        Flux<Integer> autoCo = source.publish().autoConnect(2);
//        autoCo.subscribe(System.out::println, e -> {}, () -> {});
//        System.out.println("subscribed first");
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("subscribing second");
//        autoCo.subscribe(System.out::println, e -> {}, () -> {});
    }

    /**
     * buffer list集成发射结果
     */
    private static void bufferCondition() {
//        Flux.range(1, 10)
//                .buffer(5,3)
//                .subscribe(System.out::println);

        Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
                .bufferWhile(i -> i % 2 == 0)
                .subscribe(System.out::println);
    }

    /**
     * 延迟消费, 线程异步执行
     */
    public static void testDelayElement() {
        Flux.just("A", "B", "C")
                .delayElements(Duration.ofSeconds(1))
                .subscribe(v -> log.info("current thread is :{} current value is {}", Thread.currentThread().getName(), v));

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void hotColdHandlerTest() {
        // cold condition 未被订阅推送者，不会执行任何操作
//        Flux<String> source = Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
//                .doOnNext(System.out::println)
//                .filter(s -> s.startsWith("o"))
//                .map(String::toUpperCase);
//
//        System.out.println("*************************");
//        source.subscribe(d -> System.out.println("Subscriber 1: "+d));
//        System.out.println("*************************");
//        source.subscribe(d -> System.out.println("Subscriber 2: "+d));

        // hot condition 动态添加数据
//        UnicastProcessor<String> hotSource = UnicastProcessor.create();
//        Flux<String> hotFlux = hotSource.publish()
//                .autoConnect()
//                .map(s -> {
//                    System.out.println("to convert string is " + s);
//                    return s.toUpperCase();
//                });
//        // 动态添加订阅制
//        hotFlux.subscribe(d -> System.out.println("Subscriber 1 to Hot Source: " + d));
//        hotSource.onNext("blue");
//        hotSource.onNext("green");
//
//        hotFlux.subscribe(d -> System.out.println("Subscriber 2 to Hot Source: " + d));
//        hotSource.onNext("orange");
//        hotSource.onNext("purple");
//        hotSource.onComplete();

        // new api 支持多个
        Sinks.Many<String> hotSource = Sinks.many().multicast().onBackpressureBuffer();
        Flux<String> hotFlux = hotSource.asFlux()
                .map(s -> {
                    System.out.println("to convert string is " + s);
                    return s.toUpperCase();
                });

        // 动态添加订阅制
        hotFlux.subscribe(d -> System.out.println("Subscriber 1 to Hot Source: " + d));
        hotSource.emitNext("blue", Sinks.EmitFailureHandler.FAIL_FAST);
        hotSource.emitNext("green", Sinks.EmitFailureHandler.FAIL_FAST);

        hotFlux.subscribe(d -> System.out.println("Subscriber 2 to Hot Source: " + d));
        hotSource.emitNext("orange", Sinks.EmitFailureHandler.FAIL_FAST);
        hotSource.emitNext("purple", Sinks.EmitFailureHandler.FAIL_FAST);
        hotSource.emitComplete(Sinks.EmitFailureHandler.FAIL_FAST);
    }

    /**
     * 消费异常重新订阅
     */
    public static void retryHandler() {
        Flux.interval(Duration.ofMillis(1000))
                .map(s -> {
                    log.info("current tick is {}", s);
                    if (s < 3) {
                        return "tick " + s;
                    }
                    throw new RuntimeException("boom");
                })
                .retry(2) // 重试2次
                .elapsed()
                .subscribe(System.out::println, System.out::println);

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void errorHandlerCondition() {
        // error Consumer 异常消息处理
//        Flux.just(1, 2, 3, 4, 5, 0, 6, 7, 9)
//                .map(i -> i * 2)
//                .map(i -> 100 / i)
//                .subscribe(System.out::println, Throwable::printStackTrace);

        // 异常返回固定值
//        Flux.just(1, 2, 3, 4, 5, 0, 6, 7, 9)
//                .map(i -> i * 2)
//                .map(i -> 100 / i)
//                .onErrorReturn(6666)
//                .subscribe(System.out::println);

        // error 合并Flux, 合并以后的Flux 任然产生异常
//        Flux.just(1, 2, 3, 4, 5, 0, 6, 7, 9)
//                .map(i -> i * 2)
//                .map(i -> 100 / i)
//                .onErrorResume(error -> {
//                    System.out.println("getting error " + error.getMessage());
//                    return Flux.just(666);
//                }).subscribe(System.out::println, Throwable::printStackTrace);
//
//        Flux.just(1, 2, 3, 4, 5, 0, 6, 7, 9)
//                .map(i -> i * 2)
//                .map(i -> 100 / i)
//                .onErrorResume(error -> {
//                    System.out.println("getting error " + error.getMessage());
//                    return Flux.error(new RuntimeException("zero error"));
//                }).subscribe(System.out::println);

        // finally 消费者
//        Flux.just(1, 2, 3, 4, 5, 0, 6, 7, 9)
//                .map(i -> i * 2)
//                .map(i -> 100 / i)
//                .doFinally(signalType -> {
//                    System.out.println(signalType);
//                }).subscribe(System.out::println);
    }

    /**
     * subscribeOn 消费者线程池中执行消费任务
     */
    public static void subscribeOnTest() {
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> flux = Flux
                .range(1, 1000)
                .map(i -> {
                    System.out.println("map1 current thread is " + Thread.currentThread().getName());
                    return i + 10;
                })
                .subscribeOn(s)
                .map(i -> {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("map2 current thread is " + Thread.currentThread().getName());
                    return "value is " + i;
                });

        for (int i = 0; i < 4; i++) {
            flux.subscribe(System.out::println);
        }
    }

    /**
     * 发布者消息推送进入线程池消费
     */
    public static void publishOnTest() {
        // 并行线程执行
//        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);
//        Scheduler s = Schedulers.immediate(); // 当前线程执行
//        Scheduler s = Schedulers.newSingle("single_new_test"); // 单线程执行
//        Scheduler s = Schedulers.newElastic("elastic_new");
        Scheduler s = Schedulers.newBoundedElastic(10, 10, "bounded_elastic");

        final Flux<String> flux = Flux
                .range(1, 10)
                .map(i -> {
                    int value = i * 10;
                    log.info("map1 current thread is {} value is {}", Thread.currentThread().getName(), value);
                    return value;
                })
                .publishOn(s)
                .map(i -> {
                    log.info("map2 current thread is {} value is {}", Thread.currentThread().getName(), i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "value " + i;
                });

        // 多线程并行发射消息
        for (int i = 0; i < 4; i++) {
            new Thread(() -> flux.subscribe(System.out::println), String.format("single_test_push_on_thread" + i)).start();
        }
    }

    /**
     * flux 映射Handler, 完成处理和下游释放
     */
    public static void fluxHandleConfig() {
        Flux.just(1, 2, 3, 4).handle((value, sink) -> {
            log.info("handler sink value is {}", value);
            sink.next(value);
        }).subscribe(System.out::println);
    }

    /**
     * emitter 自定义发射器，支持多线程推送。
     * PUSH模式, 多线程推送
     * PUSH_PULL 合并emit, 模拟单线程顺序消费
     */
    public static void fluxCreateConfig() {
        // 默认 push_pull 模式, 支持多线程推送，但是消费是单线程的
        Flux.create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        }, FluxSink.OverflowStrategy.DROP).subscribe(System.out::println);

        // push模式, 多线程生产，多线程消费
        Flux.push(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        }).subscribe(System.out::println);
    }

    /**
     * auto generate 自动生成
     */
    public static void fluxGenerateFunc() {
        Flux.generate(() -> 1, (state, sink) -> {
            sink.next(state * 3);
            if (state >= 10) {
                sink.complete();
            }
            return state + 1;
        }, s -> System.out.println("state consumer is " + s)).subscribe(System.out::println);
    }

    /**
     * 自定义Base Subscriber
     */
    public static void baseSubscribeOutput() {
        Flux.range(1, 100).subscribe(new BaseSubscriber<>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("hookOnSubscribe");
                request(1);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("current test all value is " + value);
                if (value > 51) {
                    // 停止消费
                    cancel();
                }
                request(1);
            }
        });
    }

    public static void subscribeMethodVoid() {
        // FluxRange 循环Loop 推送消费Event
//        Flux.range(100, 10)
//                .subscribe(System.out::println);

        // map filter 完成封装
//        Flux.range(1, 100)
//                .filter(value -> value < 20)
//                .map(Integer::doubleValue)
//                .subscribe(System.out::println, System.out::println, () -> System.out.println("complete"));

        // single value 推送
        Mono.just(100).subscribe(System.out::println);
    }
}
