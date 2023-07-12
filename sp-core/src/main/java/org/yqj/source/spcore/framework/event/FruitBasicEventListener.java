package org.yqj.source.spcore.framework.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2021/6/7
 * Email: yaoqijunmail@foxmail.com
 */
//@Component
@Slf4j
@Order(4)
public class FruitBasicEventListener implements ApplicationListener<FruitEvent> {

    @Override
    public void onApplicationEvent(FruitEvent event) {
        log.info("interface fruit basic event listener gain event :{}", event);
    }
}
