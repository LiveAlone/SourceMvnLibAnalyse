package org.yqj.source.actuator.health;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/12/10
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Endpoint(id = "readiness")
public class ReadinessEndpoints {

    @ReadOperation
    public Readiness getInfo() {
        return new Readiness("health", "test");
    }


    @AllArgsConstructor
    @Data
    public static class Readiness {
        private String health = "health";

        private String name;
    }
}
