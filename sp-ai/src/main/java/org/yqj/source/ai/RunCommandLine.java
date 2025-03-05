package org.yqj.source.ai;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.moonshot.MoonshotChatModel;
import org.springframework.ai.moonshot.MoonshotChatOptions;
import org.springframework.ai.moonshot.api.MoonshotApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/6
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class RunCommandLine implements CommandLineRunner {

    @Resource
    private MoonshotChatModel chatModel;

    @Override
    public void run(String... args) throws Exception {
        try {
            // 同步调用返回结果内容
            ChatResponse response = chatModel.call(
                    new Prompt(
                            "列举中国的五个城市名称",
                            MoonshotChatOptions.builder()
                                    .model(MoonshotApi.ChatModel.MOONSHOT_V1_8K.getValue())
                                    .temperature(0.5)
                                    .build()
                    ));
            log.info("ai chat result is response: {}", response.getResult().getOutput().getText());

            // Steam 流式调用, 顺序返回结果内容
//            chatModel.stream(new Prompt("Generate the names of 5 famous pirates.",
//                    MoonshotChatOptions.builder()
//                            .model(MoonshotApi.ChatModel.MOONSHOT_V1_8K.getValue())
//                            .temperature(0.5).build()
//            )).subscribe(response -> {
//                log.info("ai chat result is response: {}", response.getResult().getOutput().getText());
//            });
        } catch (Exception e) {
            log.error("ai chat error is happen", e);
        }
    }
}
