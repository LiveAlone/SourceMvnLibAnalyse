package org.yqj.source.zk.curator.doc.latch;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.utils.CloseableUtils;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/8/28
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class LeaderLatchClient implements Runnable {

    private final LeaderLatch leaderLatch;

    public LeaderLatchClient(CuratorFramework client, String latchPath) {
        leaderLatch = new LeaderLatch(client, latchPath);
    }

    @Override
    public void run() {
        try {
            leaderLatch.start();
            // 1. lead ship test
            for (int i = 0; i < 30; i++) {
                Thread.sleep(1000);
                log.info("has lead ship :{}", leaderLatch.hasLeadership());
            }
            leaderLatch.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
