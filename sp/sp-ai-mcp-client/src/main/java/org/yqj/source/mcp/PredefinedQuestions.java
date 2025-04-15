package org.yqj.source.mcp;

import io.modelcontextprotocol.client.McpSyncClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.ai.zhipuai.ZhiPuAiChatOptions;
import org.springframework.ai.zhipuai.api.ZhiPuAiApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * @author 10126730
 * Date: 2025/3/6 19:19
 * Description:
 */
@Component
@Slf4j
public class PredefinedQuestions implements CommandLineRunner {

    @Resource
    private McpSyncClient mcpClient;

    @Resource
    private ChatClient.Builder chatClientBuilder;

    @Resource
    private ConfigurableApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        try {
            mcpClient.listTools().tools().forEach(tool -> {
                log.info("tool name is: name:{} desc:{}", tool.name(), tool.description());
            });

//            var chatClient = chatClientBuilder
//                    .defaultTools(new SyncMcpToolCallbackProvider(mcpClient))
//                    .build();

//            System.out.println("Running predefined questions with AI model responses:\n");

            // Question 1
//            String question1 = "Can you explain the content of the /Users/user/workspace/src/github/SourceMvnLibAnalyse/sp/sp-ai-mcp-client/target/spring-ai-mcp-overview.txt file?";
//            System.out.println("QUESTION: " + question1);
//            System.out.println("ASSISTANT: " + chatClient.prompt(question1).call().content());

            // Question 2
//            String question2 = "Pleses summarize the content of the /Users/user/workspace/src/github/SourceMvnLibAnalyse/sp/sp-ai-mcp-client/target/spring-ai-mcp-overview.txt file and store it a new /Users/user/workspace/src/github/SourceMvnLibAnalyse/sp/sp-ai-mcp-client/target/summary.md as Markdown format?";
//            System.out.println("\nQUESTION: " + question2);
//            System.out.println("ASSISTANT: " +
//                    chatClient.prompt(question2).call().content());

            context.close();

        } catch (Exception e) {
            log.error("ai chat error is happen", e);
        }
    }
}
