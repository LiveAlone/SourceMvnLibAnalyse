package org.yqj.source.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaoqijun.
 * Date:2016-02-05
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
@Disabled
public class FlowTest {

    @BeforeAll
    public static void init() {
        List<FlowRule> rules = new ArrayList<>();

        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(10);      // qps 数量限制

        FlowRule rule2 = new FlowRule();
        rule2.setResource("HelloWorld2");
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule2.setCount(20);      // qps 数量限制

        rules.add(rule);
        rules.add(rule2);

        FlowRuleManager.loadRules(rules);
    }

    @Test
    public void limitQpsTest() throws Exception {
        while (true) {
            try (Entry entry = SphU.entry("HelloWorld")) {
                System.out.println("Hello world !!");
            } catch (Exception e) {
            }

            try (Entry entry = SphU.entry("HelloWorld2")) {
                System.out.println("New Hello world !!");
            } catch (Exception e) {
            }
            Thread.sleep(10);
        }
    }

    @Test
    public void limitQps2Test() throws Exception {
        while (true) {
            if (SphO.entry("HelloWorld")) {
                System.out.println("Hello world !!");
                SphO.exit();
            }
            Thread.sleep(10);
        }
    }

    @Test
    public void differentEntryConfigTest() throws BlockException {
        ContextUtil.enter("entrance1", "appA");
        Entry nodeA = SphU.entry("nodeA");
        if (nodeA != null) {
            nodeA.exit();
        }
        ContextUtil.exit();

        ContextUtil.enter("entrance2", "appA");
        nodeA = SphU.entry("nodeA");
        if (nodeA != null) {
            nodeA.exit();
        }
        ContextUtil.exit();
    }

    @Test
    public void testSphOTest(){
        while (true){
            if (SphO.entry("HelloWorld")){
                try {
                    System.out.println("hello world! ");
                }finally {
                    SphO.exit();
                }
            }else {
                System.out.println("block");
            }
        }
    }
}
