package org.yqj.source.basic.base.collection;

import com.google.common.collect.Lists;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2021/11/17
 * Email: yaoqijunmail@foxmail.com
 */
public class StreamDemo {

    public static void main(String[] args) {

//        foreachTest();

        forkJoinTest();

    }

    private static void foreachTest() {
        Lists.newArrayList(1, 2, 3).stream().map(v -> v + 100).forEach(System.out::println);
    }

    private static void forkJoinTest() {
//        List<Integer> result = Lists.newArrayList(1, 2, 1, 123, 123, 123, 12, 5143, 3, 5612, 1, 678, 4, 834, 562, 345, 2412)
//                .stream().parallel().sorted().collect(Collectors.toList());
//        System.out.println(result);

        // 创建2000个随机数组成的数组:
        long[] array = new long[2000];
        long expectedSum = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = random();
            expectedSum += array[i];
        }
        System.out.println("Expected sum: " + expectedSum);
        // fork/join:
        ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        Long result = ForkJoinPool.commonPool().invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }

    private static class SumTask extends RecursiveTask<Long> {
        static final int THRESHOLD = 500;
        long[] array;
        int start;
        int end;

        SumTask(long[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            String threadName = Thread.currentThread().getName();
            if (end - start <= THRESHOLD) {
                // 如果任务足够小,直接计算:
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += this.array[i];
                    // 故意放慢计算速度:
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println(String.format("current thread %s calculate result is %d", threadName, sum));
                return sum;
            }
            // 任务太大,一分为二:
            int middle = (end + start) / 2;
            System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d current thread name is %s", start, end, start, middle, middle, end, threadName));
            SumTask subtask1 = new SumTask(this.array, start, middle);
            SumTask subtask2 = new SumTask(this.array, middle, end);
            invokeAll(subtask1, subtask2);
            Long subresult1 = subtask1.join();
            Long subresult2 = subtask2.join();
            Long result = subresult1 + subresult2;
            System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
            return result;
        }
    }

    static Random random = new Random(0);

    static long random() {
        return random.nextInt(10000);
    }
}
