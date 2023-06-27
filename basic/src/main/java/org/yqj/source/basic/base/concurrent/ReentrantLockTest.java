package org.yqj.source.basic.base.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yaoqijun on 2017-11-22.
 */
public class ReentrantLockTest {
    public static void main(String[] args) throws Exception{
        final ReentrantLock lock = new ReentrantLock();

        ValueTest val = new ValueTest();

        CommonConcurrent.multiplyThreadStart(() -> {
            for (int i=0;i<10;i++){
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " get lock");
                    val.add();
                }finally {
                    lock.unlock();
                }
            }
        }, 100);

        Thread.sleep(5000);

        System.out.println(val.getVal());
    }

    public static class ValueTest{
        private int val = 0;

        public void add(){
            for (int i=1; i<=10; i++){
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
}
