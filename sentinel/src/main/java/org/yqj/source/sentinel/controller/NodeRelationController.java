package org.yqj.source.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.ContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/7/31
 * Email: yaoqijunmail@foxmail.com
 */
@RestController
@Slf4j
public class NodeRelationController {

    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public String test1() throws Exception {
        ContextUtil.enter("contextA", "originA");
        Entry entryA = SphU.entry("entryA");
        Entry entryB = SphU.entry("entryB");
        entryB.exit();
        entryA.exit();
        Entry entryC = SphU.entry("entryC");
        entryC.exit();
        return "success";
    }

    /**
     * 模拟降级请求状态
     *
     * @param expType 0 - 正常请求  1 - 超时请求  2 - exp 异常请求
     * @return
     */
    @RequestMapping(value = "test2", method = RequestMethod.GET)
    public String test2() throws Exception {
        ContextUtil.enter("contextB", "originB");
        Entry entryA = SphU.entry("entryA");
        entryA.exit();
        return "success";
    }
}
