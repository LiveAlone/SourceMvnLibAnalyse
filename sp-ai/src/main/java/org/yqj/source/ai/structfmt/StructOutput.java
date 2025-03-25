package org.yqj.source.ai.structfmt;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 10126730
 * Date: 2025/3/24 15:28
 * Description:
 */
//@Component
@Slf4j
public class StructOutput implements CommandLineRunner {

    @Resource
    private ChatModel chatModel;

    @Override
    public void run(String... args) throws Exception {
        // 1 格式化文本内容输出
//        ActorsFilms actorsFilms = ChatClient.create(chatModel).prompt()
//                .user(u -> u.text("Generate the filmography of 5 movies for {actor}.")
//                        .param("actor", "周星驰"))
//                .call()
//                .entity(ActorsFilms.class);
//        log.info("actorsFilms: {}", actorsFilms);

        // 2 list
//        List<ActorsFilms> actorsFilms = ChatClient.create(chatModel).prompt()
//                .user("Generate the filmography of 5 movies for 周星驰 and 周润发.")
//                .call()
//                .entity(new ParameterizedTypeReference<List<ActorsFilms>>() {});
//        actorsFilms.stream().forEach(actor -> log.info("actorFilms: {}", actor));

        // 3 map
        Map<String, Object> result = ChatClient.create(chatModel).prompt()
                .user(u -> u.text("列举一些中国演员和他们的代表作品，key 为演员名字，value 为代表作品列表。"))
                .call()
                .entity(new ParameterizedTypeReference<Map<String, Object>>() {});
        result.forEach((key, value) -> log.info("key: {}, value: {}", key, value));
    }
}
