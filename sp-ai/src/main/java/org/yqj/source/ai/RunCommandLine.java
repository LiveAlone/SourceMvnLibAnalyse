package org.yqj.source.ai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

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

//    @Resource
//    private ZhiPuAiChatModel zhiPuAiChatModel;

    @Override
    public void run(String... args) throws Exception {
//        ChatClient chatClient = ChatClient.create(zhiPuAiChatModel);
//        String content = chatClient.prompt("hello").call().content();
//        log.info("content: {}", content);

//        formatResponse(chatClient);

//        Flux<String> output = chatClient.prompt()
//                .user("Tell me a joke")
//                .stream().content();
//        output.subscribe(System.out::print);

    }

    private void formatResponse(ChatClient chatClient) {
        // AI 通过entity 自动格式化 json 数据
//        ActorFilms actorFilms = chatClient.prompt()
//                .user("Generate the filmography for a random actor.")
//                .call()
//                .entity(ActorFilms.class);
//        log.info("actorFilms: {}", actorFilms);

//        List<ActorFilms> actorFilms = chatClient.prompt()
//                .user("Generate the filmography of 5 movies for Tom Hanks and Bill Murray.")
//                .call()
//                .entity(new ParameterizedTypeReference<List<ActorFilms>>() {});
//        log.info("actorFilms: {}", actorFilms);

        // struct format
//        var converter = new BeanOutputConverter<>(new ParameterizedTypeReference<List<ActorFilms>>() {});
//        log.info("format response :{}", converter.getFormat());
//        Flux<String> flux = chatClient.prompt()
//                .user(u -> u.text("""
//                        Generate the filmography for a random actor.
//                        {format}
//                      """).param("format", converter.getFormat()))
//                .stream()
//                .content();
//        String content = flux.collectList().block().stream().collect(Collectors.joining());
//        log.info("content: {}", content);
//        List<ActorFilms> actorFilms = converter.convert(content);
//        log.info("actorFilms: {}", actorFilms);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class ActorFilms {
        private String actor;
        private List<String> movies;
    }
}
