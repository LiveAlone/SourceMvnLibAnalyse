package org.yqj.source.lang.aop.cglib;

import net.sf.cglib.core.KeyFactory;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/27
 * Email: yaoqijunmail@foxmail.com
 */
public class KeySample {

    public static void KeySampleExample() {
        MyFactory myFactory = (MyFactory) KeyFactory.create(MyFactory.class);
        Object yao = myFactory.newInstance(100, "yao");
        Object qi = myFactory.newInstance(100, "qi");
        Object yao2 = myFactory.newInstance(100, "yao");
        System.out.println(yao.equals(qi));
        System.out.println(yao.equals(yao2));
    }

    public interface MyFactory {
        Object newInstance(int a, String d);
    }
}
