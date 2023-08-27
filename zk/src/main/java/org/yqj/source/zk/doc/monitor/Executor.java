package org.yqj.source.zk.doc.monitor;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Description:
 *
 * @author qjyao
 * @date 2023/8/27
 */
@Slf4j
public class Executor implements Watcher, Runnable, DataMonitor.DataMonitorListener{
    DataMonitor dm;
    ZooKeeper zk;

    public static void main(String[] args) {
        String hostPort = "localhost:2181";
        String znode = "/zk_test";
        try {
            new Executor(hostPort, znode).run();
        } catch (Exception e) {
            log.info("executor run exception", e);
        }
    }

    public Executor(String hostPort, String znode) throws IOException {
        zk = new ZooKeeper(hostPort, 3000, this);
        dm = new DataMonitor(zk, znode, this);
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (!dm.dead) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            log.info("executor run exception", e);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        dm.process(event);
    }

    @Override
    public void exists(byte[] data) {
        if (data == null) {
            log.info("Try to Killing process data: {}", data);
        } else {
            log.info("exists gain data: {}", data);
            log.info("zk watcher start exec sub command");
        }
    }

    @Override
    public void closing(int rc) {
        log.info("closing rc: {}", rc);
        synchronized (this) {
            notifyAll();
        }
    }
}
