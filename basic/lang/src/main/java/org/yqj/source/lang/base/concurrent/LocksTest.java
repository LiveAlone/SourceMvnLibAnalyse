package org.yqj.source.lang.base.concurrent;

import org.checkerframework.checker.units.qual.A;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/27
 * Email: yaoqijunmail@foxmail.com
 */
public class LocksTest {
    public static void main(String[] args) throws Exception {
//        ReentInterruptLockTest();

//        ReentAddValueTest();

        RWLockTest();
    }

    public static void RWLockTest() throws Exception {
        RWDictionaryDemo demo = new RWDictionaryDemo();
        demo.put("value", 100);

        AtomicInteger idx = new AtomicInteger(1);

        // 启动读线程
        CommonConcurrent.multiplyThreadStart(() -> {
            int id = idx.getAndAdd(1);

            for (int i = 0; i < 10; i++) {
                Integer v = demo.get("value");
                System.out.printf("read id :%d, get value :%d%n", id, v);
            }
        }, 10);

        // 启动写线程
        CommonConcurrent.multiplyThreadStart(() -> {
            int id = idx.getAndAdd(1);
            for (int i = 200; i < 210; i++) {
                Integer from = demo.put("value", i);
                System.out.printf("writer id :%d, from :%d, value :%d%n", id, from, i);
            }
        }, 1);

        Thread.sleep(5000);
    }

    public static void ReentAddValueTest() throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        ValueTest valueTest = new ValueTest();

        CommonConcurrent.multiplyThreadStart(() -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " get lock");
                    valueTest.add();
                } finally {
                    lock.unlock();
                }
            }
        }, 100);

        Thread.sleep(5000);

        System.out.println(valueTest.getVal());

    }

    public static class ValueTest {
        private int val = 0;

        public void add() {
            for (int i = 1; i <= 10; i++) {
                val = val + 1;
            }
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    public static void ReentInterruptLockTest() throws InterruptedException {
        IntLock intLock1 = new IntLock(1);
        IntLock intLock2 = new IntLock(2);
        Thread thread1 = new Thread(intLock1);
        Thread thread2 = new Thread(intLock2);
        thread1.start();
        thread2.start();
        Thread.sleep(10000);
        thread2.interrupt();
        System.out.println("after interrupt");
    }

    public static class IntLock implements Runnable {

        public static ReentrantLock lock1 = new ReentrantLock();

        public static ReentrantLock lock2 = new ReentrantLock();

        int lock;

        public IntLock(int value) {
            this.lock = value;
        }

        @Override
        public void run() {
            try {
                if (lock == 1) {
                    lock1.lockInterruptibly();
                    System.out.println("thread 1 get lock 1 success");
                    Thread.sleep(5000);
                    lock2.lockInterruptibly();
                    System.out.println("thread 1 get lock 2 success");
                } else {
                    lock2.lockInterruptibly();
                    System.out.println("thread 2 get lock 2 success");
                    Thread.sleep(5000);
                    lock1.lockInterruptibly();
                    System.out.println("thread 2 get lock 1 success");
                }
            } catch (InterruptedException e) {
                System.out.println(this.lock + " get exception content");
//                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    System.out.println("lock1 to unlock");
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    System.out.println("lock2 to unlock");
                    lock2.unlock();
                }
                System.out.println(this.lock + "  thread is finall content ");
            }
        }
    }
}
