package org.yqj.source.zk.curator.example;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yqj.source.zk.curator.CuratorConfig;
import org.yqj.source.zk.curator.example.leader.LeaderExampleClient;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/8/29
 */

@Component
@Slf4j
public class ExampleCommandLine implements CommandLineRunner {

    @Resource
    private CuratorConfig curatorConfig;

    @Override
    public void run(String... args) throws Exception {
        leadClient();
    }

    private void leadClient() throws Exception {
        try(CuratorFramework client = buildClient()) {
            LeaderExampleClient leaderExampleClient = new LeaderExampleClient(client, "/examples/leader", "client");
            leaderExampleClient.start();
            Thread.sleep(3600*1000);
            leaderExampleClient.close();
        }
    }

    private CuratorFramework buildClient() {
        CuratorFramework client = CuratorFrameworkFactory.newClient(curatorConfig.getConnectionString(), new ExponentialBackoffRetry(1000, 3));
        client.start();
        return client;
    }
}
