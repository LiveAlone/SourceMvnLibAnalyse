package org.yqj.source.ai.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author 10126730
 * Date: 2025/3/21 10:08
 * Description:
 */
@RestController
@Slf4j
public class ImageController {

    @Resource
    private ImageModel imageModel;

    @GetMapping("/ai/img")
    public Mono<String> generateStream(@RequestParam(value = "message", defaultValue = "哈士奇") String message) {
        return Mono.fromCallable(() -> {
            ImageResponse response = imageModel.call(
                    new ImagePrompt(message, ImageOptionsBuilder.builder().model("cogview-4").build()));
            log.info("image response is {}", response.getResult().getOutput().getUrl());
            return response.getResult().getOutput().getUrl();
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
