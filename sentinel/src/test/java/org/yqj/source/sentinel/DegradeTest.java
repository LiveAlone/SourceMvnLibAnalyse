package org.yqj.source.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/8/4
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class DegradeTest {

    @BeforeAll
    public static void initTest() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule("DegradeController");

        rule.setGrade(CircuitBreakerStrategy.ERROR_RATIO.getType()).setCount(0.2); // Threshold is 50% error ratio
        rule.setMinRequestAmount(10); // 最小请求数量
        rule.setStatIntervalMs(5000)   // 统计时间长度 5s
                .setTimeWindow(10); // 熔断时长 10s

        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }

    @Test
    public void testSingleDegrade() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {

            // 写法错误, AutoClose 优先执行
//            try (Entry entry = SphU.entry("DegradeController")) {
//                Thread.sleep(ThreadLocalRandom.current().nextInt(300));
//                if (i % 2 == 0) {
//                    throw new RuntimeException("error");
//                }
//            } catch (Throwable t) {
//                log.error("degrade controller fail cause:", t);
//                if (!BlockException.isBlockException(t)) {
//                    Tracer.trace(t);
//                }
//            }

            Entry entry = null;
            try {
                entry = SphU.entry("DegradeController");
                Thread.sleep(ThreadLocalRandom.current().nextInt(300));
                if (i % 2 == 0) {
                    throw new RuntimeException("error");
                }
            } catch (Throwable t) {
                log.error("degrade controller fail cause:", t);
                if (!BlockException.isBlockException(t)) {
                    Tracer.trace(t);
                }
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }
            Thread.sleep(10);
        }
    }
}
