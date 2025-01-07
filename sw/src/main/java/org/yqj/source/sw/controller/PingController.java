package org.yqj.source.sw.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.meter.Counter;
import org.apache.skywalking.apm.toolkit.meter.MeterFactory;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2021/11/26
 * Email: yaoqijunmail@foxmail.com
 */
@RestController
@Slf4j
public class PingController {

    private Counter addressEndpointCount = MeterFactory.counter("api_count").tag("endpoint", "address").mode(Counter.Mode.INCREMENT).build();

    @RequestMapping(value = "/address", method = RequestMethod.GET, name = "address")
    public String address(@RequestParam("param") String param) throws Exception {
        for (int i = 0; i < 10; i++) {
            processBusiness(String.format("task_name%d", i));
        }
        addressEndpointCount.increment(1);
        return InetAddress.getLocalHost().getHostName() + param;
    }

    @Trace(operationName = "addressLocalTrace")
    @Tag(key = "arg", value = "arg[0]")
    @Tag(key = "result", value = "returnedObj")
    private int processBusiness(String processTaskName) throws InterruptedException {
        int taskProcessTime = ThreadLocalRandom.current().nextInt(100);
        Thread.sleep(taskProcessTime);
        ActiveSpan.info(String.format("task %s finish with cost %d ms", processTaskName, taskProcessTime));
        log.info("process task finish with traceId:{} segmentId:{}, spanId:{} taskName{}, case:{} ms", TraceContext.traceId(), TraceContext.segmentId(), TraceContext.spanId(), processTaskName, taskProcessTime);
        return taskProcessTime;
    }
}
