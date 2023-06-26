package org.yqj.source.pattern.balking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by yaoqijun on 2017-06-09.
 */
public class App {
    public static void main(String[] args) {
        final WashingMachine washingMachine = new WashingMachine();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.execute(washingMachine::wash);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException ie) {
            System.out.println(("ERROR: Waiting on executor service shutdown!"));
        }
    }
}
