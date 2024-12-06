package org.yqj.source.lang.base.concurrent;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/27
 * Email: yaoqijunmail@foxmail.com
 */
public class AtomicTest {

    public static void main(String[] args) {
        atomicAdder();
    }

    public static void atomicAdder() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(10);
        System.out.println(longAdder.sum());
    }
}
