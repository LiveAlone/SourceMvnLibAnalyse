package org.yqj.source.pattern.abstractdocument;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Document {

    void put(String name, Object object);

    Object get(String name);

    // 映射目标类型
    <T>Stream<T> children(String key, Function<Map<String, Object>, T> construct);
}
