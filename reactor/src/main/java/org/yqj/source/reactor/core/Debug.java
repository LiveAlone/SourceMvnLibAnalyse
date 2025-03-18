package org.yqj.source.reactor.core;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @author 10126730
 * Date: 2025/3/17 20:16
 * Description:
 */
@Slf4j
public class Debug {
    public static void main(String[] args) {
        debug();
//
//        debugProd();
    }

    private static void debugProd() {
        // 2 product
//        ReactorDebugAgent.init();
//        ReactorDebugAgent.processExistingClasses();

        Flux<Integer> flux = Flux.range(1, 10)
                .log()
                .take(3);
        flux.subscribe();
    }

    private static void debug() {
        // 1 debug 模式调试过程
//        Hooks.onOperatorDebug();

        // Create a Flux with an error
        Flux.range(1, 100)
                .filter(value -> value % 2 == 0)
                .map(value -> value * 2)
                .map(value -> {
                    if (value > 100) {
                        throw new RuntimeException("Test error at value: " + value);
                    }
                    return value;
                })
                .map(value -> "Value: " + value)
                .subscribe(
                        System.out::println,
                        error -> {
                            log.info("*********** print stack trace ***********");
                            error.printStackTrace();
                        },
                        () -> System.out.println("Completed")
                );
    }
}
