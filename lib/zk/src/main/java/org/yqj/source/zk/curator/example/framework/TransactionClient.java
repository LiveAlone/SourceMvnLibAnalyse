package org.yqj.source.zk.curator.example.framework;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;

import java.util.Collection;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/8/29
 */
@Slf4j
public class TransactionClient {
    public static Collection<CuratorTransactionResult> transaction(CuratorFramework client) throws Exception {
        // 通过zk方式支持事务操作

        CuratorOp createOp = client.transactionOp().create().forPath("/a/path", "some data".getBytes());
        CuratorOp setDataOp = client.transactionOp().setData().forPath("/another/path", "other data".getBytes());
        CuratorOp deleteOp = client.transactionOp().delete().forPath("/yet/another/path");

        Collection<CuratorTransactionResult> results = client.transaction().forOperations(createOp, setDataOp, deleteOp);


        for (CuratorTransactionResult result : results) {
            System.out.println(result.getForPath() + " - " + result.getType());
        }
        return results;
    }
}
