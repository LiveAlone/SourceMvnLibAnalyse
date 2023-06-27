package org.yqj.source.basic.base.lang;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/27
 * Email: yaoqijunmail@foxmail.com
 */
public class StringExample {
    public static void main(String[] args) {
        String origin = "yaoqijun";
        String s1 = new String(origin);
        String s2 = new String(origin);
        System.out.println(s1 == s2);

        System.out.println(Character.valueOf('A')==Character.valueOf('A'));
        System.out.println(Character.valueOf((char)542) == Character.valueOf((char)542));
        System.out.println(Character.MAX_VALUE);
    }
}
