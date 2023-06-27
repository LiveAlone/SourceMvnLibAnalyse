package org.yqj.source.basic.base.concurrent;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/2/18
 * Email: yaoqijunmail@foxmail.com
 */
public class Common {

    public void atomicAdder() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(10);
        System.out.println(longAdder.sum());

        ThreadLocalRandom.current(); // force initialization
        Thread th = Thread.currentThread();
    }

}
