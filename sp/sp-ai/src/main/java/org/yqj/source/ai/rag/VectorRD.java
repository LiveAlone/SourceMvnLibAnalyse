package org.yqj.source.ai.rag;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author 10126730
 * Date: 2025/3/21 14:33
 * Description:
 */
//@Component
@Slf4j
public class VectorRD implements CommandLineRunner {

    @Resource
    private VectorStore vectorStore;

    @Override
    public void run(String... args) throws Exception {
//        saveEmbeddingData();
        searchRelatedData();
    }

    private void searchRelatedData() {
        // 1 search
        System.out.println("***********************************************************");
        String s1 = "寻求刺激与自然美景结合的旅行地点建议。";
        List<Document> results = this.vectorStore.similaritySearch(SearchRequest.builder().query(s1)
                .topK(2).build());
        System.out.println(s1);
        results.forEach(document -> System.out.println(document.getText()));
        System.out.println("***********************************************************");
        String s2 = "我想要一次历史的文化体验之旅，请推荐。";
        results = this.vectorStore.similaritySearch(SearchRequest.builder().query(s2)
                .topK(2).build());
        System.out.println(s2);
        results.forEach(document -> System.out.println(document.getText()));
        System.out.println("***********************************************************");

        // 2 filter express
//        FilterExpressionBuilder b = new FilterExpressionBuilder();
//        List<Document> results = vectorStore.similaritySearch(SearchRequest.builder()
//                .query("Spring").topK(3).filterExpression("meta in ['one']").build());
////                .filterExpression("country in ['UK', 'NL'] && year >= 2020")
//        results.forEach(document -> {
//            log.info("document is {}", document);
//        });

    }

    private void saveEmbeddingData() {
        // 通过Redis-stack启动实例
//        List<Document> documents = List.of(
//                new Document("Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!!", Map.of("meta", "one")),
//                new Document("The World is Big and Salvation Lurks Around the Corner", Map.of("meta", "two")),
//                new Document("You walk forward facing the past and you turn back toward the future.", Map.of("meta", "three")));
//        vectorStore.add(documents);

        List<String> content = Lists.newArrayList(
                "探寻古老与现代交融的魅力：京都不仅有千年的神社与庭院，还有现代艺术和创意料理。",
                "巴厘岛，不只是海滩与冲浪的天堂，更有深藏于山林间的静谧村落等你去发现。",
                "穿梭在巴黎的小巷中，每一扇门后都有一个故事，美食、艺术、历史在这里交织。",
                "赴一场北欧的极光之旅，芬兰的玻璃屋、挪威的峡湾，让你与自然最亲密的对话。",
                "墨西哥城的热情如同它的美食一样让人难以忘怀，历史文化与现代生活在这里完美融合。",
                "澳大利亚大堡礁的潜水体验，探索珊瑚礁的奥秘，与海龟和五彩缤纷的鱼群共游。",
                "埃及金字塔之旅，不仅是一次穿越历史的探索，还有机会领略尼罗河的壮观景色。",
                "在尼泊尔徒步旅行，感受喜马拉雅山脉的雄伟，探访古老的寺庙和神秘的佛教文化。",
                "意大利托斯卡纳的乡村之旅，品味地道的美食，探索文艺复兴时期的传奇故事。",
                "新西兰的户外探险之旅，从跳伞、漂流到徒步，体验极致的自然风光和刺激的冒险活动。");
        content.forEach(doc -> {
            vectorStore.add(Collections.singletonList(new Document(doc, Collections.emptyMap())));
        });
    }
}
