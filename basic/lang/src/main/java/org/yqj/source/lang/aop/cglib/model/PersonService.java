package org.yqj.source.lang.aop.cglib.model;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/26
 * Email: yaoqijunmail@foxmail.com
 */
public class PersonService {
    public String sayHello(String name) {
        return "Hello " + name;
    }

    public String lengthOfName(String name) {
        return String.valueOf(name.length());
    }
}
