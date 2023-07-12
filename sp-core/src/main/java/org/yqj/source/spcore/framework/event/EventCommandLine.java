package org.yqj.source.spcore.framework.event;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/7/12
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class EventCommandLine implements CommandLineRunner {

    @Resource
    private FruitEventPublisherService fruitEventPublisherService;

    @Override
    public void run(String... args) throws Exception {
        fruitEventPublisherService.publishEvent();
    }
}
