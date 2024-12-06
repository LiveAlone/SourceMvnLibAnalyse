package org.yqj.source.lang.aop.jdk.model;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/27
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class Person implements Runner, Coder, Worker{

    @Override
    public void code(String name) {
        log.info("person :{} is coding", name);
    }

    @Override
    public void run(String name) {
        log.info("person :{} is running", name);
    }

    @Override
    public void work(String name) {
        log.info("person :{} is working", name);
    }
}
