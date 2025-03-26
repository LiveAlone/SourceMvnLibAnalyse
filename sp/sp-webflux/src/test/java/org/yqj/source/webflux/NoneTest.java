package org.yqj.source.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/6
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class NoneTest {
//    @Test
    public void testNone() {
        log.info("***************** debug test *****************");
        Flux<Integer> flux = Flux.range(100, 10);
        log.info("flux :{}", flux.getClass().getName());
    }
}
