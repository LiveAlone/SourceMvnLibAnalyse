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
public class Dog extends CommonAnimal{

    @Override
    public void run(String name) {
        log.info("dog :{} is running", name);
    }
}
