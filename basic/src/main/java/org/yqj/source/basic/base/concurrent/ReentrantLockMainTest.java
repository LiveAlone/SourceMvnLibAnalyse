package org.yqj.source.basic.base.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMainTest {
    public static void main(String[] args) throws InterruptedException {
        IntLock intLock1 = new IntLock(1);
        IntLock intLock2 = new IntLock(2);
        Thread thread1 = new Thread(intLock1);
        Thread thread2 = new Thread(intLock2);
        thread1.start(); thread2.start();
        Thread.sleep(10000);
        thread2.interrupt();
        System.out.println("after interrupt");
    }

    public static class IntLock implements Runnable{

        public static ReentrantLock lock1 = new ReentrantLock();

        public static ReentrantLock lock2 = new ReentrantLock();

        int lock;

        public IntLock(int value){
            this.lock = value;
        }

        @Override
        public void run() {
            try {
                if (lock == 1){
                    lock1.lockInterruptibly();
                    System.out.println("thread 1 get lock 1 success");
                    Thread.sleep(5000);
                    lock2.lockInterruptibly();
                    System.out.println("thread 1 get lock 2 success");
                }else {
                    lock2.lockInterruptibly();
                    System.out.println("thread 2 get lock 2 success");
                    Thread.sleep(5000);
                    lock1.lockInterruptibly();
                    System.out.println("thread 2 get lock 1 success");
                }
            } catch (InterruptedException e){
                System.out.println(this.lock + " get exception content");
//                e.printStackTrace();
            }finally {
                if (lock1.isHeldByCurrentThread()){
                    System.out.println("lock1 to unlock");
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()){
                    System.out.println("lock2 to unlock");
                    lock2.unlock();
                }
                System.out.println(this.lock + "  thread is finall content ");
            }
        }
    }

}
