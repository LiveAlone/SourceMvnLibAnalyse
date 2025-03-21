package org.yqj.source.ai.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.zhipuai.ZhiPuAiChatOptions;
import org.springframework.ai.zhipuai.api.ZhiPuAiApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author 10126730
 * Date: 2025/3/20 20:51
 * Description:
 */
@RestController
@Slf4j
public class ChatController {

    @Resource
    private ChatModel chatModel;

    @GetMapping("/ai/gen")
    public Mono<String> generate(@RequestParam(value = "message", defaultValue = "列举中国安徽的十个城市") String message) {
        // 同步非阻塞方式完成请求
        return Mono.fromCallable(() -> {
            ChatResponse response = chatModel.call(
                    new Prompt(message,
                            ZhiPuAiChatOptions.builder().model(ZhiPuAiApi.ChatModel.GLM_3_Turbo.getValue())
                                    .temperature(0.5).build()
                    ));
            return response.getResult().getOutput().getText();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping("/ai/gens")
    public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "列举中国安徽的十个城市") String message) {
        var prompt = new Prompt(new UserMessage(message));
        return this.chatModel.stream(prompt).map(v -> v.getResult().getOutput().getText());
    }


}
