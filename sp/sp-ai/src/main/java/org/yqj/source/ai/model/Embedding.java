package org.yqj.source.ai.model;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.zhipuai.ZhiPuAiEmbeddingModel;
import org.springframework.ai.zhipuai.ZhiPuAiEmbeddingOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 10126730
 * Date: 2025/3/7 09:22
 * Description:
 */
//@Component
@Slf4j
public class Embedding implements CommandLineRunner {

    @Resource
    private ZhiPuAiEmbeddingModel embeddingModel;

    @Override
    public void run(String... args) throws Exception {
        EmbeddingResponse embeddingResponse = embeddingModel.call(new EmbeddingRequest(List.of("Hello World", "World is big and salvation is near"),
                ZhiPuAiEmbeddingOptions.builder().model("embedding-2").build()));
        embeddingResponse.getResults().forEach(embedding -> {
            log.info("embedding result is {}", embedding.getOutput());
        });
        log.info("embedding response is {}", embeddingResponse);
    }
}
