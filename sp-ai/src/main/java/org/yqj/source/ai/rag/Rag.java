package org.yqj.source.ai.rag;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author 10126730
 * Date: 2025/3/21 17:44
 * Description:
 */
//@Component
@Slf4j
public class Rag implements CommandLineRunner {

    @Resource
    private ChatModel chatModel;

    @Resource
    private VectorStore vectorStore;

    @Override
    public void run(String... args) throws Exception {
        String userText = "想要一次安静的旅行，请推荐。";
//        ChatResponse response = ChatClient.builder(chatModel)
//                .build().prompt()
//                .advisors(new QuestionAnswerAdvisor(vectorStore))
//                .user(userText)
//                .call()
//                .chatResponse();
//        log.info("response is {}", response.getResult().getOutput().getText());

//        var qaAdvisor = new QuestionAnswerAdvisor(this.vectorStore,
//                SearchRequest.builder().similarityThreshold(0.8d).topK(6).build());
//        ChatResponse response = ChatClient.builder(chatModel)
//                .build().prompt()
//                .advisors(qaAdvisor)
//                .user(userText)
//                .call()
//                .chatResponse();
//        log.info("response is {}", response.getResult().getOutput().getText());


        // 动态参数检索
//        ChatClient chatClient = ChatClient.builder(chatModel)
//                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.builder().build()))
//                .build();
//        String content = chatClient.prompt()
//                .user(userText)
//                .advisors(a -> a.param(QuestionAnswerAdvisor.FILTER_EXPRESSION, "type == 'Spring'"))
//                .call()
//                .content();
//        log.info("response is {}", content);
    }
}
