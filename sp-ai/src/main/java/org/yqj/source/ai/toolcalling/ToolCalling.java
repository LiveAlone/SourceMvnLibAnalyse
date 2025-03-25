package org.yqj.source.ai.toolcalling;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author 10126730
 * Date: 2025/3/24 16:12
 * Description:
 */
@Component
@Slf4j
public class ToolCalling implements CommandLineRunner {

    @Resource
    private ChatModel chatModel;

    @Override
    public void run(String... args) throws Exception {
        // 1 获取明天日期
//        String response = ChatClient.create(chatModel)
//                .prompt("明天是什么日期?")
//                .tools(new DateTimeTools())
//                .call()
//                .content();
//        log.info("tomorrow is {}", response);

        // 2 action 采取行动
        String response = ChatClient.create(chatModel)
                .prompt("给我设置一个一小时以后的闹钟")
                .tools(new DateTimeTools())
                .call()
                .content();
        System.out.println(response);
    }
}
