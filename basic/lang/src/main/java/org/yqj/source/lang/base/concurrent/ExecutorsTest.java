package org.yqj.source.lang.base.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/27
 * Email: yaoqijunmail@foxmail.com
 */
public class ExecutorsTest {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorsTest.class);

    private static int threadIndex = 0;

    private static int jobNameIndex = 0;

    public static void main(String[] args) {
        cacheThreadPoolTest();
    }

    private static void cacheThreadPoolTest() {

        // job 异常以后 停止执行， 线程继续使用
//        ExecutorService executorService = Executors.newFixedThreadPool(2, r -> new Thread(r, "localThread-" + (++threadIndex)));
//        addJobToRunnableJob(executorService, 1, true);
//        addJobToRunnableJob(executorService, 5, false);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2, r -> new Thread(r, "localThread-" + (++threadIndex)));
        scheduleJobToRunnableJob(scheduledExecutorService, 1, true);
        scheduleJobToRunnableJob(scheduledExecutorService, 1, false);
    }

    private static void scheduleJobToRunnableJob(ScheduledExecutorService scheduledExecutorService, int count, boolean isException) {
        for (int i = 0; i < count; i++) {
            scheduledExecutorService.scheduleWithFixedDelay(new RunnableJob(String.valueOf(++jobNameIndex), 5000L, isException), 0, 2L, TimeUnit.SECONDS);
        }
    }

    private static void addJobToRunnableJob(ExecutorService executorService, int count, boolean isException) {
        for (int i = 0; i < count; i++) {
            executorService.submit(new RunnableJob(String.valueOf(++jobNameIndex), 5000L, isException));
        }
    }

    // 启动运行时候的Job
    public static class RunnableJob implements Runnable {

        private Long runTime;

        private String jobName;

        private boolean isException;

        public RunnableJob(String name, Long runTime, boolean isException) {
            this.runTime = runTime;
            this.jobName = name;
            this.isException = isException;
        }


        @Override
        public void run() {
            try {
                logger.info("start Thread:{}, jobName:{}", Thread.currentThread().getName(), jobName, runTime);
                Thread.sleep(runTime);
                logger.info("end Thread:{}, jobName:{}", Thread.currentThread().getName(), jobName, runTime);
            } catch (Exception e) {
                logger.error("job:{} execute error, cause:{}", jobName, e);
            }
            if (isException) {
                throw new IllegalStateException("error is exception content");
            }
        }
    }
}
