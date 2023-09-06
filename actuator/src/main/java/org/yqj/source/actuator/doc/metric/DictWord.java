package org.yqj.source.actuator.doc.metric;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/6
 */
@Component
public class DictWord {
    public DictWord(MeterRegistry registry, ApplicationContext applicationContext) {
        registry.gauge("env.count", Collections.emptyList(), applicationContext.getBeanDefinitionNames().length);
    }
}
