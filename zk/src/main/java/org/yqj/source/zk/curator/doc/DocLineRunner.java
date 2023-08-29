package org.yqj.source.zk.curator.doc;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yqj.source.zk.curator.CuratorConfig;
import org.yqj.source.zk.curator.doc.latch.LeaderLatchClient;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/8/28
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class DocLineRunner implements CommandLineRunner {

    @Resource
    private CuratorConfig curatorConfig;

    @Override
    public void run(String... args) throws Exception {
        try (CuratorFramework client = build()) {
            // 1. lead ship
            LeaderLatchClient leaderLatchClient = new LeaderLatchClient(client, "/curator/leader-latch");
            leaderLatchClient.run();
        }
    }

    private CuratorFramework build() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(curatorConfig.getConnectionString(), retryPolicy);
        client.start();
        return client;
    }
}
