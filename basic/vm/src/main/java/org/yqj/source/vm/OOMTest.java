package org.yqj.source.vm;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2020/8/8
 * Email: yaoqijunmail@foxmail.com
 */
public class OOMTest {

    public static void main(String[] args) {
        List objectList = new ArrayList<>();
        while (true){
            byte[] b = new byte[1024*1024];
            objectList.add(b);
            System.out.println("finish create b 1M block size");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
