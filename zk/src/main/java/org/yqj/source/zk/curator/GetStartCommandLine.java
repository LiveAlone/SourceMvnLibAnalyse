package org.yqj.source.zk.curator;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/8/28
 */
//@Component
@Slf4j
public class GetStartCommandLine implements CommandLineRunner {

    @Resource
    private CuratorConfig curatorConfig;

    @Override
    public void run(String... args) throws Exception {
//        createDir();

//        lock();

//        leadSelector();
    }

    private void leadSelector() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(curatorConfig.getConnectionString(), retryPolicy);
        LeaderSelector selector = null;
        try {
            client.start();
            LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
                @Override
                public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                    log.info("take leader ship success");
                }
            };

            selector = new LeaderSelector(client, "/curator/leader", listener);
//            selector.autoRequeue();  // not required, but this is behavior that you will probably expect
            selector.start();

            Thread.sleep(60 * 1000);
            log.info("finish lead selector condition");
        } finally {
            if (Objects.nonNull(selector)) {
                CloseableUtils.closeQuietly(selector);
            }
            CloseableUtils.closeQuietly(client);
        }
    }

    private void lock() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(curatorConfig.getConnectionString(), retryPolicy);

        try {
            client.start();
            InterProcessMutex lock = new InterProcessMutex(client, "/curator/lock-");
            if (lock.acquire(10, TimeUnit.MINUTES)) {
                try {
                    log.info("gain lock success");
                    Thread.sleep(30 * 1000);
                } finally {
                    lock.release();
                }
                log.info("release lock success");
            }
        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }

    private void createDir() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(curatorConfig.getConnectionString(), retryPolicy);

        try {
            client.start();
//            log.info("client start success :{}", client.checkExists().forPath("/"));
            String content = client.create().forPath("/my-test", "test".getBytes());
            log.info("create node result: {}", content);
        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }
}
