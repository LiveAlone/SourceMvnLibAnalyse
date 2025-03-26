package org.yqj.source.actuator.health;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/12/10
 * Email: yaoqijunmail@foxmail.com
 */
@Component
public class LocalReporter implements MeterBinder {

    private DistributionSummary distributionSummary;

    @Override
    public void bindTo(MeterRegistry registry) {
        distributionSummary = DistributionSummary.builder("local_summary")
                .description("test local summary config")
                .publishPercentiles(0.5, 0.9, 0.95, 0.99, 1)
                .distributionStatisticExpiry(Duration.ofSeconds(1L))
                .register(registry);
    }

    public void report(double value) {
        distributionSummary.record(value);
    }
}
