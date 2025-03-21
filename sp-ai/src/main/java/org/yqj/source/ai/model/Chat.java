package org.yqj.source.ai.model;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.ai.zhipuai.ZhiPuAiChatOptions;
import org.springframework.ai.zhipuai.api.ZhiPuAiApi;
import org.springframework.boot.CommandLineRunner;
import reactor.core.publisher.Flux;

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
                    new Prompt("列举中国安徽的十个城市",
                            ZhiPuAiChatOptions.builder().model(ZhiPuAiApi.ChatModel.GLM_3_Turbo.getValue())
                                    .temperature(0.5).build()
                    ));
            log.info("ai chat result is response: {}", response.getResult().getOutput().getText());

            log.info("ai stream chat print ************************************");
            // Steam 流式调用, 顺序返回结果内容
            Flux<ChatResponse> fluxResponse = chatModel.stream(
                    new Prompt("列举中国安徽的十个城市",
                            ZhiPuAiChatOptions.builder().model(ZhiPuAiApi.ChatModel.GLM_3_Turbo.getValue())
                                    .temperature(0.5).build()
                    ));
            fluxResponse.subscribe(chatResponse -> System.out.print(chatResponse.getResult().getOutput().getText()));
        } catch (Exception e) {
            log.error("ai chat error is happen", e);
        }
    }
}
