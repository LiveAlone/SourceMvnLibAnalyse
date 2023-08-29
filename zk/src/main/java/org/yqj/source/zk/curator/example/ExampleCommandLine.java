package org.yqj.source.zk.curator.example;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yqj.source.zk.curator.CuratorConfig;
import org.yqj.source.zk.curator.example.async.AsyncExamples;
import org.yqj.source.zk.curator.example.cache.CuratorCacheExample;
import org.yqj.source.zk.curator.example.cache.PathCacheExample;
import org.yqj.source.zk.curator.example.cache.TreeCacheExample;
import org.yqj.source.zk.curator.example.framework.CRUDClient;
import org.yqj.source.zk.curator.example.framework.TransactionClient;
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
//        leadClient();

//        curatorFrameworkApi();

//        syncSupport();

        cacheSupport();
    }

    private void cacheSupport() throws Exception {
        try (CuratorFramework client = CuratorFrameworkFactory.newClient(curatorConfig.getConnectionString(), new ExponentialBackoffRetry(1000, 3))) {
            // 1. 缓存
//            CuratorCacheExample.run(client);

            // 2. 缓存树形结构
//            TreeCacheExample.run(client);

            // 3. Path 路径缓存
            PathCacheExample.run(client);
        }
    }

    private void syncSupport() throws Exception {
        try (CuratorFramework client = buildClient()) {
//            AsyncExamples.create(client, "/examples", "example".getBytes());

            AsyncExamples.createThenWatchSimple(client, "/examples/async");
        }
    }

    private void curatorFrameworkApi() throws Exception {
        try (CuratorFramework client = buildClient()) {
            // 事务方式操作
            TransactionClient.transaction(client);
        }

//        try(CuratorFramework client = buildClient()) {
//            // 1. create node
////            CRUDClient.create(client, "/examples", "example".getBytes());
//
//            // 2. create 临时节点
////            CRUDClient.createEphemeral(client, "/examples/ephemeral", "ephemeral".getBytes());
////            CRUDClient.createEphemeralSequential(client, "/examples/ephemeral", "ephemeral".getBytes());
////            Thread.sleep(30000);
//
//            // 3. 幂等创建节点
//            CRUDClient.createIdempotent(client, "/examples/idempotent", "idempotent".getBytes());
//
//            // 4. setData setDataAsync 同步异步方式填充数据
//
//            // 5. withVersion 保证版本号一致性。
//        }
    }

    private void leadClient() throws Exception {
        try (CuratorFramework client = buildClient()) {
            LeaderExampleClient leaderExampleClient = new LeaderExampleClient(client, "/examples/leader", "client");
            leaderExampleClient.start();
            Thread.sleep(3600 * 1000);
            leaderExampleClient.close();
        }
    }

    private CuratorFramework buildClient() {
        CuratorFramework client = CuratorFrameworkFactory.newClient(curatorConfig.getConnectionString(), new ExponentialBackoffRetry(1000, 3));
        client.start();
        return client;
    }
}
