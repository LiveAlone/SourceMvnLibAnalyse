package org.yqj.source.mapstruct;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/6
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class BootDemoApplication {
    public static void main(String[] args) {
        Source source = new Source();
        source.setFoo(42);
        source.setBar(23L);
        source.setZip(73);
        source.setBaz(123L);
        source.setQax(456);

        Target target = SourceTargetMapper.INSTANCE.sourceToTarget(source);
        log.info("target is {}", target);
    }
}
