package org.yqj.source.zk.curator.example.framework;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

import java.util.List;

/**
 * Description: 基于Framework 构建查询方式
 *
 * @author qjyao
 * @date 2023/8/29
 */
@Slf4j
public class CRUDClient {
    public static void create(CuratorFramework client, String path, byte[] payload) throws Exception {
        client.create().forPath(path, payload);
    }

    public static void createEphemeral(CuratorFramework client, String path, byte[] payload) throws Exception {
        client.create().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
    }

    /**
     * 创建节点支持生成随机名称
     * @param client
     * @param path
     * @param payload
     * @return
     * @throws Exception
     */
    public static String createEphemeralSequential(CuratorFramework client, String path, byte[] payload)
            throws Exception {
        return client.create()
                .withProtection()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath(path, payload);
    }

    // 幂等创建节点
    public static void createIdempotent(CuratorFramework client, String path, byte[] payload) throws Exception {
        client.create().idempotent().forPath(path, payload);
    }

    public static void setData(CuratorFramework client, String path, byte[] payload) throws Exception {
        client.setData().forPath(path, payload);
    }

    public static void setDataAsync(CuratorFramework client, String path, byte[] payload) throws Exception {
        CuratorListener listener = new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                // examine event for details
            }
        };
        client.getCuratorListenable().addListener(listener);
        client.setData().inBackground().forPath(path, payload);
    }

    public static void setDataAsyncWithCallback(
            CuratorFramework client, BackgroundCallback callback, String path, byte[] payload) throws Exception {
        // this is another method of getting notification of an async completion
        client.setData().inBackground(callback).forPath(path, payload);
    }

    public static void setDataIdempotent(CuratorFramework client, String path, byte[] payload, int currentVersion)
            throws Exception {
        /*
         * This will set the given ZNode with the given data idempotently, meaning that if the initial setData
         * failed transiently, it will be retried and behave as if the first setData never happened, even if the
         * first setData actually succeeded on the server but the client didn't know it.
         * In other words, if currentVersion == X and payload = P, this will return success if the znode ends
         * up in the state (version == X+1 && data == P).
         * If withVersion is not specified, it will end up with success so long as the data == P, no matter the znode version.
         */
        client.setData().idempotent().withVersion(currentVersion).forPath(path, payload);
        client.setData().idempotent().forPath(path, payload);
    }

    public static void delete(CuratorFramework client, String path) throws Exception {
        // delete the given node
        client.delete().forPath(path);
    }

    public static void guaranteedDelete(CuratorFramework client, String path) throws Exception {
        // delete the given node and guarantee that it completes

        /*
           Guaranteed Delete

           Solves this edge case: deleting a node can fail due to connection issues. Further, if the node was
           ephemeral, the node will not get auto-deleted as the session is still valid. This can wreak havoc
           with lock implementations.


           When guaranteed is set, Curator will record failed node deletions and attempt to delete them in the
           background until successful. NOTE: you will still get an exception when the deletion fails. But, you
           can be assured that as long as the CuratorFramework instance is open attempts will be made to delete
           the node.
        */

        client.delete().guaranteed().forPath(path);
    }

    public static void deleteIdempotent(CuratorFramework client, String path, int currentVersion) throws Exception {
        /*
         * This will delete the given ZNode with the given data idempotently, meaning that if the initial delete
         * failed transiently, it will be retried and behave as if the first delete never happened, even if the
         * first delete actually succeeded on the server but the client didn't know it.
         * In other words, if currentVersion == X, this will return success if the znode ends up deleted, and will retry after
         * connection loss if the version the znode's version is still X.
         * If withVersion is not specified, it will end up successful so long as the node is deleted eventually.
         * Kind of like guaranteed but not in the background.
         * For deletes this is equivalent to the older quietly() behavior, but it is also provided under idempotent() for compatibility with Create/SetData.
         */
        client.delete().idempotent().withVersion(currentVersion).forPath(path);
        client.delete().idempotent().forPath(path);
        client.delete().quietly().withVersion(currentVersion).forPath(path);
        client.delete().quietly().forPath(path);
    }

    public static List<String> watchedGetChildren(CuratorFramework client, String path) throws Exception {
        /**
         * Get children and set a watcher on the node. The watcher notification will come through the
         * CuratorListener (see setDataAsync() above).
         */
        return client.getChildren().watched().forPath(path);
    }

    public static List<String> watchedGetChildren(CuratorFramework client, String path, Watcher watcher)
            throws Exception {
        /**
         * Get children and set the given watcher on the node.
         */
        return client.getChildren().usingWatcher(watcher).forPath(path);
    }
}
