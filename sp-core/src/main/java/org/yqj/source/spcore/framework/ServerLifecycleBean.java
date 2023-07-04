package org.yqj.source.spcore.framework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/12/10
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class ServerLifecycleBean implements SmartLifecycle {

    @Override
    public void start() {
        log.info("server lifecycle bean start");
    }

    @Override
    public void stop() {
        log.info("server lifecycle bean stop");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
