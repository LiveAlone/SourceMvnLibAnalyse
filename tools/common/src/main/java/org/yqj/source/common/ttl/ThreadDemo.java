package org.yqj.source.common.ttl;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 10126730
 * Date: 2024/9/18 19:47
 * Description:
 */
@Slf4j
public class ThreadDemo {

    private final static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    private final static InheritableThreadLocal<String> stringInheritableThreadLocal = new InheritableThreadLocal<>();
    public static void main(String[] args) throws Exception{
        stringThreadLocal.set("yao");
        stringInheritableThreadLocal.set("qi");
        log.info("set thread local success");

        // 1. 本地线程
//        printStringMessage();

        // 2. 异步子线程执行
        printStringMessageAsync();
    }

    public static void printStringMessageAsync() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> future = executorService.submit(ThreadDemo::printStringMessage);
        future.get();
        executorService.shutdown();
    }

    public static void printStringMessage() {
        log.info("gain thread local is :{}", stringThreadLocal.get());
        log.info("gain inheritable thread local is :{}", stringInheritableThreadLocal.get());
    }
}
