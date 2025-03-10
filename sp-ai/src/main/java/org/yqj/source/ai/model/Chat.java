package org.yqj.source.ai.model;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.ai.zhipuai.ZhiPuAiChatOptions;
import org.springframework.ai.zhipuai.api.ZhiPuAiApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author 10126730
 * Date: 2025/3/6 19:19
 * Description:
 */
//@Component
@Slf4j
public class Chat implements CommandLineRunner {

    @Resource
    private ZhiPuAiChatModel chatModel;

    @Override
    public void run(String... args) throws Exception {
        try {
            // 同步调用返回结果内容
            ChatResponse response = chatModel.call(
                    new Prompt(
                            "列举中国安徽的十个城市",
                            ZhiPuAiChatOptions.builder()
                                    .model(ZhiPuAiApi.ChatModel.GLM_3_Turbo.getValue())
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
