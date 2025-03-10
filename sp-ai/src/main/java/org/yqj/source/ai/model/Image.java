package org.yqj.source.ai.model;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.zhipuai.ZhiPuAiImageModel;
import org.springframework.ai.zhipuai.ZhiPuAiImageOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author 10126730
 * Date: 2025/3/7 09:42
 * Description:
 */
//@Component
@Slf4j
public class Image implements CommandLineRunner {

    @Resource
    private ZhiPuAiImageModel zhiPuAiImageModel;

    @Override
    public void run(String... args) throws Exception {
        ZhiPuAiImageOptions options = ZhiPuAiImageOptions.builder().model("cogview-4").build();
        ImageResponse response = zhiPuAiImageModel.call(
                new ImagePrompt("一只搞笑的哈士奇", options));
        log.info("image response is {}", response.getResult());
    }
}
