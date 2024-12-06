package org.yqj.source.lang.aop.cglib.model;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/27
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public abstract class AbstractAnimal implements Animal{

    @Override
    public void eat(String name) {
        log.info("abstract animal :{} is eating", name);
    }

    @Override
    public void drink(String name) {
        log.info("abstract animal :{} is drinking", name);
    }
}
