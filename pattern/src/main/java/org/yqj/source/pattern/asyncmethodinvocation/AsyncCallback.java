package org.yqj.source.pattern.asyncmethodinvocation;

import java.util.Optional;

/**
 * Created by yaoqijun on 2017-06-09.
 */
public interface AsyncCallback<T> {

    void onComplete(T value, Optional<Exception> ex);

}
