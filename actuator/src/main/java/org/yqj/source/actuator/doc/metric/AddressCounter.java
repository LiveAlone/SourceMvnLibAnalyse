package org.yqj.source.actuator.doc.metric;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.atomic.LongAdder;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/9/6
 */
@Component
public class AddressCounter {

    private final LongAdder longAdder = new LongAdder();

    public AddressCounter(MeterRegistry registry) {
        registry.gauge("address.count", Collections.emptyList(), longAdder);
    }

    public void add() {
        longAdder.add(1L);
    }
}
