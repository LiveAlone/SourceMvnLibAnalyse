package org.yqj.source.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/7/31
 * Email: yaoqijunmail@foxmail.com
 */
@RestController
@Slf4j
public class SentinelController {

    @RequestMapping(value = "authority", method = RequestMethod.GET)
    public String authority(@RequestParam(value = "origin", defaultValue = "none") String origin) {
        ContextUtil.enter("default", origin);
        try(Entry entry = SphU.entry("AuthorityController")) {
            Thread.sleep(ThreadLocalRandom.current().nextInt(300));
            return "success";
        } catch (Exception e) {
            log.error("authority controller fail {} cause:", origin, e);
            return e.toString();
        }
    }

    /**
     * 模拟降级请求状态
     * @param expType 0 - 正常请求  1 - 超时请求  2 - exp 异常请求
     * @return
     */
    @RequestMapping(value = "degrade", method = RequestMethod.GET)
    public String degrade(@RequestParam(value = "expType", required = false) int expType) {
        Entry entry = null;
        try {
            entry = SphU.entry("DegradeController");
            Thread.sleep(ThreadLocalRandom.current().nextInt(300));
            if (expType > 0) {
                throw new RuntimeException("process fail");
            }
            return "success";
        } catch (Exception e) {
            log.error("degrade controller fail cause:", e);
            if (!BlockException.isBlockException(e)) {
                Tracer.trace(e);
            }
            return e.toString();
        }finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }

    /**
     * 流量控制
     * @return
     */
    @RequestMapping(value = "flow", method = RequestMethod.GET)
    public String flowController() {
        try (Entry entry = SphU.entry("FlowController")) {
            Thread.sleep(ThreadLocalRandom.current().nextInt(300));
            return "success";
        } catch (Exception e) {
            log.error("flow controller fail cause:", e);
            return e.toString();
        }
    }

    /**
     * 流控来源
     * @return
     */
    @RequestMapping(value = "flowSource", method = RequestMethod.GET)
    public String flowControllerSource(@RequestParam("name") String name,
                                       @RequestParam("origin") String origin) {
        ContextUtil.enter(name, origin);
        try (Entry entry = SphU.entry("FlowController")) {
            Thread.sleep(ThreadLocalRandom.current().nextInt(300));
            return "success";
        } catch (Exception e) {
            log.error("flow controller fail cause:", e);
            return e.toString();
        }
    }
}
