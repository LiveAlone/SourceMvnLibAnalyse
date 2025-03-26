package org.yqj.source.sentinel.controller;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/7/31
 * Email: yaoqijunmail@foxmail.com
 */
@Configuration
public class SentinelConfiguration {

    @PostConstruct
    public void init() {
//        flowControllerInit();

//        degradeControllerInit();

//        authorityControllerInit();
    }

    public void authorityControllerInit() {
        AuthorityRule rule = new AuthorityRule();
        rule.setResource("AuthorityController");
        rule.setStrategy(RuleConstant.AUTHORITY_WHITE);
        rule.setLimitApp("yao,qi,jun");

        AuthorityRuleManager.loadRules(Collections.singletonList(rule));
    }

    public void degradeControllerInit() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule("DegradeController");

        rule.setGrade(CircuitBreakerStrategy.ERROR_RATIO.getType()).setCount(0.2); // Threshold is 50% error ratio
        rule.setMinRequestAmount(10); // 最小请求数量
        rule.setStatIntervalMs(5000)   // 统计时间长度 5s
                .setTimeWindow(10); // 熔断时长 10s

        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }

    private void flowControllerInit() {
        List<FlowRule> rules = new ArrayList<>();

        // single rule
        FlowRule rule = new FlowRule();
        rule.setResource("FlowController");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);

        rules.add(rule);

        FlowRuleManager.loadRules(rules);
    }
}
