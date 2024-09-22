package org.yqj.source.lettuce.cmd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author 10126730
 * Date: 2024/8/27 17:11
 * Description:
 */
@Component
@Slf4j
public class JmsCmd implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        // 消息推送 pub/sub 支持

        // Stream 流式数据推送消费
    }
}
