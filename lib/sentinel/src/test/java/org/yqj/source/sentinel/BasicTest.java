package org.yqj.source.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/8/4
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
@Disabled
public class BasicTest {

    @Test
    public void simpleTest() throws BlockException {
        ContextUtil.enter("contextA", "originA");
        Entry entryA = SphU.entry("entryA");
        Entry entryB = SphU.entry("entryB");
        entryB.exit();
        entryA.exit();
        ContextUtil.exit();
    }
}
