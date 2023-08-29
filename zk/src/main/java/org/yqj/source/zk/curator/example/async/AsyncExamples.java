/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.yqj.source.zk.curator.example.async;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.async.AsyncCuratorFramework;
import org.apache.curator.x.async.AsyncEventException;
import org.apache.curator.x.async.WatchMode;
import org.apache.zookeeper.WatchedEvent;

import java.util.concurrent.CompletionStage;

/**
 * Examples using the asynchronous DSL
 */
public class AsyncExamples {
    public static AsyncCuratorFramework wrap(CuratorFramework client) {
        return AsyncCuratorFramework.wrap(client);
    }

    public static void create(CuratorFramework client, String path, byte[] payload) {
        AsyncCuratorFramework async = AsyncCuratorFramework.wrap(client);
        async.create().forPath(path, payload).whenComplete((name, exception) -> {
            if (exception != null) {
                // there was a problem
                exception.printStackTrace();
            } else {
                System.out.println(Thread.currentThread().getName() + " Created node name is: " + name);
            }
        });
    }

    public static void createThenWatch(CuratorFramework client, String path) {
        AsyncCuratorFramework async = AsyncCuratorFramework.wrap(client);
        async.create().forPath(path).whenComplete((name, exception) -> {
            if (exception != null) {
                // there was a problem creating the node
                exception.printStackTrace();
            } else {
                handleWatchedStage(async.watched().checkExists().forPath(path).event());
            }
        });
    }

    public static void createThenWatchSimple(CuratorFramework client, String path) {
        AsyncCuratorFramework async = AsyncCuratorFramework.wrap(client);

        async.create().forPath(path).whenComplete((name, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
            } else {
                async.with(WatchMode.successOnly)
                        .watched()
                        .checkExists()
                        .forPath(path)
                        .event()
                        .thenAccept(event -> {
                            System.out.println(Thread.currentThread().getName() + "watched success");
                            System.out.println(event.getType());
                            System.out.println(event);
                        });
            }
        });
    }

    private static void handleWatchedStage(CompletionStage<WatchedEvent> watchedStage) {
        watchedStage.thenAccept(event -> {
            System.out.println(Thread.currentThread().getName() + "watched success");
            System.out.println(event.getType());
            System.out.println(event);
        });
        watchedStage.exceptionally(exception -> {
            AsyncEventException asyncEx = (AsyncEventException) exception;
            asyncEx.printStackTrace(); // handle the error as needed
            handleWatchedStage(asyncEx.reset());
            return null;
        });
    }
}
