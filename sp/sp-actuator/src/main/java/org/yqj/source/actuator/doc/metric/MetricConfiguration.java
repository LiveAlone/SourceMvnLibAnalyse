package org.yqj.source.actuator.doc.metric;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/6
 */
@Configuration
public class MetricConfiguration {

    private List<String> list = Lists.newArrayList();

    @Bean
    public MeterBinder queueSize() {
        return (registry) -> Gauge.builder("queueSize", list::size).register(registry);
    }
}
