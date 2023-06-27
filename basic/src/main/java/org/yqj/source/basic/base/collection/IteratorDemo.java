package org.yqj.source.basic.base.collection;


import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author yaoqijun on 2017-12-19.
 */
public class IteratorDemo {
    public static void main(String[] args) {
        List<Integer> its = Lists.newArrayList(123,523,4,234,123,34);

        // java8 支持接口定义 default 默认函数
        its.iterator().forEachRemaining(System.out::println);
    }
}
