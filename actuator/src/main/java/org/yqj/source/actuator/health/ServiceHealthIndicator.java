package org.yqj.source.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/12/10
 * Email: yaoqijunmail@foxmail.com
 */
@Component
public class ServiceHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // 自定义服务健康状态
        return Health.up().build();
    }
}
