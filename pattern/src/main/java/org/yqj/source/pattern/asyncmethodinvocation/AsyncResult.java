package org.yqj.source.pattern.asyncmethodinvocation;

import java.util.concurrent.ExecutionException;

/**
 * Created by yaoqijun on 2017-06-09.
 */
public interface AsyncResult<T> {

    boolean isCompleted();

    T getValue() throws ExecutionException;

    void await() throws InterruptedException;
}
