package org.yqj.source.spcore.framework.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Map;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/7/5
 * Email: yaoqijunmail@foxmail.com
 */
public class LocalEnvProcessor implements EnvironmentPostProcessor, Ordered {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        PropertySource<Map<String, Object>> propertySource = new MapPropertySource("local", Map.of("processor.value", "hello_processor"));
        environment.getPropertySources().addLast(propertySource);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
