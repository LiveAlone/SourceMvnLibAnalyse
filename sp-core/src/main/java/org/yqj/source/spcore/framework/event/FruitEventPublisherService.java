package org.yqj.source.spcore.framework.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2021/6/7
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class FruitEventPublisherService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent() {
        log.info("fruit event publish service start, thread: {}", Thread.currentThread().getName());
//        applicationEventPublisher.publishEvent(new FruitEvent(this, "common"));
//        applicationEventPublisher.publishEvent(new AppleEvent(this, "common", "shandong"));
//        applicationEventPublisher.publishEvent(new OrangeEvent(this, "common", true));
        applicationEventPublisher.publishEvent(new NorthAppleEvent(this, "common"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
