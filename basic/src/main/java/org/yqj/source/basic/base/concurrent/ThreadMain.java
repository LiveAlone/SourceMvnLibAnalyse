package org.yqj.source.basic.base.concurrent;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2021/11/14
 * Email: yaoqijunmail@foxmail.com
 */
public class ThreadMain {

    public static void main(String[] args) throws Exception {
        tryToInterruptThread();
    }

    public static final void tryToInterruptThread() throws InterruptedException {
        // sleep interrupt
//        Thread cur = new Thread(() -> {
//            long val = 0L;
//            try {
//                Thread.sleep(20000);
//            }catch (Exception e) {
//                System.out.println("gain error message is " + e.getMessage());
//                System.out.println("exception value is :" + val);
//            }
//        });

        // interrupt 执行中线程
        Thread cur = new Thread(() -> {
            long val = 0L;
            try {
                while (true) {
                    val += 1;
                }
            }catch (Exception e) {
                System.out.println("gain error message is " + e.getMessage());
                System.out.println("exception value is :" + val);
            }
        });

        // 挂起线程不会interrupt 异常状态
//        Thread cur = new Thread(()->{
//            System.out.println("current thread is to starting");
//            LockSupport.park();
//            System.out.println("current thread is alive");
//        });

        cur.start();
        cur.interrupt();
        Thread.sleep(10000);
        System.out.println("interrupt finish all");
    }

}
