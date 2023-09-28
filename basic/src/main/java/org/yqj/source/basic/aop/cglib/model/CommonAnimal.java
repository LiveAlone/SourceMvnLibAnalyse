package org.yqj.source.basic.aop.cglib.model;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/27
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class CommonAnimal implements Animal{

    @Override
    public void eat(String name) {
        log.info("common animal :{} eat", name);
    }

    @Override
    public void drink(String name) {
        log.info("common animal :{} drink", name);
    }

    @Override
    public void run(String name) {
        log.info("common animal :{} run", name);
    }
}
