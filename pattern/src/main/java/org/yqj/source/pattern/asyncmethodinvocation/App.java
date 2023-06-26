package org.yqj.source.pattern.asyncmethodinvocation;

import java.util.concurrent.Callable;

/**
 * Created by yaoqijun on 2017-06-09.
 */
public class App {

    public static void main(String[] args) throws Exception {
        // construct a new executor that will run async tasks
        AsyncExecutor executor = new ThreadAsyncExecutor();

        // start few async tasks with varying processing times, two last with callback handlers
        AsyncResult<Integer> asyncResult1 = executor.startProcess(lazyval(10, 500));
        AsyncResult<String> asyncResult2 = executor.startProcess(lazyval("test", 300));
        AsyncResult<Long> asyncResult3 = executor.startProcess(lazyval(50L, 700));
        AsyncResult<Integer> asyncResult4 = executor.startProcess(lazyval(20, 400), callback("Callback result 4"));
        AsyncResult<String> asyncResult5 = executor.startProcess(lazyval("callback", 600), callback("Callback result 5"));

        // emulate processing in the current thread while async tasks are running in their own threads
        Thread.sleep(350); // Oh boy I'm working hard here
        System.out.println(("Some hard work done"));

        // wait for completion of the tasks
        Integer result1 = executor.endProcess(asyncResult1);
        String result2 = executor.endProcess(asyncResult2);
        Long result3 = executor.endProcess(asyncResult3);
        asyncResult4.await();
        asyncResult5.await();

        // log the results of the tasks, callbacks log immediately when complete
        System.out.println(("Result 1: " + result1));
        System.out.println(("Result 2: " + result2));
        System.out.println(("Result 3: " + result3));
    }

    private static <T> Callable<T> lazyval(T value, long delayMillis) {
        return () -> {
            Thread.sleep(delayMillis);
            System.out.println(("Task completed with: " + value));
            return value;
        };
    }

    private static <T> AsyncCallback<T> callback(String name) {
        return (value, ex) -> {
            if (ex.isPresent()) {
                System.out.println((name + " failed: " + ex.map(Exception::getMessage).orElse("")));
            } else {
                System.out.println(name + ": " + value);
            }
        };
    }

}
