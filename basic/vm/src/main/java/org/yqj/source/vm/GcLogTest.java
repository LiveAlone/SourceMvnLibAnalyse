package org.yqj.source.vm;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/8/8
 * Email: yaoqijunmail@foxmail.com
 */
public class GcLogTest {

    public static void main(String[] args) {
        while (true){
            byte[] b = new byte[1024*1024];
            System.out.println("finish create b 1M block size");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
