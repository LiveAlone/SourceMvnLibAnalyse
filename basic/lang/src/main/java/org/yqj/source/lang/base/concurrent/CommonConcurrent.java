package org.yqj.source.lang.base.concurrent;

/**
 * @author yaoqijun on 2017-11-22.
 */
public class CommonConcurrent {
    public static void multiplyThreadStart(Runnable runnable, int threadNumber){
        for (int i=1; i<=threadNumber; i++){
            new Thread(runnable, "thread-name"+i).start();
        }
    }
}
