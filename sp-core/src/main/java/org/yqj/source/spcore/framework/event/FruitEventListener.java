package org.yqj.source.spcore.framework.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2021/4/26
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class FruitEventListener {

    @EventListener
    @Order(2)
    public void fruitListener(FruitEvent fruitEvent) {
        log.info("fruit event listener {} get event {}", Thread.currentThread().getName(), fruitEvent);
    }

    @EventListener
    @Order(1)
    public void appleListener(AppleEvent appleEvent) {
        log.info("apple event listener {} get event {}", Thread.currentThread().getName(), appleEvent);
    }

    @EventListener
    public void orangeListener(OrangeEvent orangeEvent) {
        log.info("orange event listener {} get event {}", Thread.currentThread().getName(), orangeEvent);
    }

    @EventListener
    @Order(0)
    public void northAppleListener(NorthAppleEvent northAppleEvent) {
        log.info("north apple event listener {} get event {}", Thread.currentThread().getName(), northAppleEvent);
    }
}
