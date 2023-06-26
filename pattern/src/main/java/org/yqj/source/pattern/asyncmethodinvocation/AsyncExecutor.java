package org.yqj.source.pattern.asyncmethodinvocation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by yaoqijun on 2017-06-09.
 */
public interface AsyncExecutor {

    <T> AsyncResult<T> startProcess(Callable<T> task);

    <T> AsyncResult<T> startProcess(Callable<T> task, AsyncCallback<T> callback);

    <T> T endProcess(AsyncResult<T> asyncResult) throws ExecutionException, InterruptedException;
}

