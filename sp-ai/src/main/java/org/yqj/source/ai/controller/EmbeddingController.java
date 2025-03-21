package org.yqj.source.ai.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.zhipuai.ZhiPuAiEmbeddingOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * @author 10126730
 * Date: 2025/3/21 09:43
 * Description:
 */
@RestController
@Slf4j
public class EmbeddingController {

    @Resource
    private EmbeddingModel embeddingModel;

    /**
     * embed 内嵌 Vertor
     * @param message
     * @return
     */
    @GetMapping("/ai/embeds")
    public Mono<float[]> generateStream(@RequestParam(value = "message", defaultValue = "列举中国安徽的十个城市") String message) {
        return Mono.fromCallable(() -> {
            EmbeddingResponse embeddingResponse = embeddingModel.call(new EmbeddingRequest(
                    List.of(message),
                    ZhiPuAiEmbeddingOptions.builder().model("embedding-2").build()));
            embeddingResponse.getResults().forEach(embedding -> {
                log.info("embedding result is {}", embedding.getOutput());
            });
            return embeddingResponse.getResults().get(0).getOutput();
        }).subscribeOn(Schedulers.boundedElastic());
    }

}
