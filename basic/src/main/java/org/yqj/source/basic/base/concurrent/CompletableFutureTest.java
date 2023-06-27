package org.yqj.source.basic.base.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/3/17
 * Email: yaoqijunmail@foxmail.com
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {

//        futureSingle();

//         1. CompletableFuture price set
        completableFutureSet();

//        testCompleteOutput();

//        calculateChain();

//        runApplyAsyncConfig();

//        whenCompleteValueTestConfig();

//        testThen();

//        completableBothValueTest();
    }

    public static void completableBothValueTest(){
        ExecutorService es = Executors.newFixedThreadPool(10);
        System.out.println("main start");

        CompletableFuture<Integer> c1 = CompletableFuture.supplyAsync(CompletableFutureTest::calculateRandomValue, es);
        CompletableFuture<Integer> c2 = CompletableFuture.supplyAsync(CompletableFutureTest::calculateRandomValue, es);

        c2.thenAcceptBoth(c1, (a, b)->{
            System.out.println(a);
            System.out.println(b);
            System.out.println("finish Thread is is " + Thread.currentThread().getName());
        });

        System.out.println("main finish");
        es.shutdown();
    }

    public static void testThen() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(10);
        System.out.println("main start");

        CompletableFuture.supplyAsync(CompletableFutureTest::calculateRandomValue, es)
                .thenApply(s->"*****" + s.toString())
                .whenComplete((v, e) -> System.out.println(v));
        System.out.println("main finish");
        es.shutdown();
    }

    public static void whenCompleteValueTestConfig() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        System.out.println("main start");
//        CompletableFuture<Integer> sum = CompletableFuture.supplyAsync(CompletableFutureTest::calculateRandomValue, es)
//                .whenComplete((a, e)-> {
//                    System.out.println("start calculate random value in thread is " + Thread.currentThread().getName());
//                    System.out.println(a);
//                });
//        CompletableFuture.completedFuture(100).whenComplete(CompletableFutureTest::outputValue);
//        CompletableFuture.completedFuture(100).whenCompleteAsync(CompletableFutureTest::outputValue, es);
        System.out.println("main finish");
        es.shutdown();
    }

    public static void runApplyAsyncConfig() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(10);
        System.out.println("main start");

//        CompletableFuture.runAsync(()->{
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("current calculate value in thread is " + Thread.currentThread().getName());
//        }, es);

//        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("current calculate value in thread is " + Thread.currentThread().getName());
//            return 100;
//        }, es);
//        System.out.println(completableFuture.isDone());
//        System.out.println(completableFuture.get());

        System.out.println("main finish");
        es.shutdown();

    }

    private static <T, U> void outputValue(T value, U e) {
        System.out.println("start calculate random value in thread is " + Thread.currentThread().getName());
        System.out.println(value);
    }

    private static int calculateRandomValue() {
        System.out.println("start calculate random value in thread is " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextInt(1000);
    }

    public static void testCompleteOutput() {
//        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
//        new Thread(()->{
//            try {
//                System.out.println("thread1 get value is " + completableFuture.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(()->{
//            try {
//                System.out.println("thread2 get value is " + completableFuture.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        System.out.println("starting");
//        completableFuture.complete(666D);
//        System.out.println("ending");

        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("123test");
        System.out.println("start");
        completableFuture.whenComplete((v, exp) -> {
            System.out.println("First Step Thread Value is " + Thread.currentThread().getName());
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("value");
        });
        System.out.println("end");
    }

    public static void completableFutureSet() throws Exception {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        Thread processThread = new Thread(() -> {
            System.out.println("First Step Thread Value is " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete(123D);
        }, "processThread");
        processThread.start();
//        System.out.println("main started" + completableFuture.get());
        System.out.println("main started");

        CompletableFuture<Double> d2 = completableFuture.whenComplete((a, s) -> {
            // 这里线程，1  get complete， main 当前线程执行, 2 not complete main 继续执行, 等待complete 选择线程执行。
            System.out.println("Complete Step Thread Value is " + Thread.currentThread().getName());
            System.out.println(a);
            System.out.println(s);
        });
//        System.out.println(d2.get());
        System.out.println("main finish");
    }

    public static void futureSingle() throws Exception {
        System.out.println("main start");
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<Integer> f = es.submit(() -> {
            Thread.sleep(2000);
            return 100;
        });
        System.out.println(f.isDone());
        System.out.println(f.get());
        System.out.println("main finish");
        es.shutdown();
    }
}
