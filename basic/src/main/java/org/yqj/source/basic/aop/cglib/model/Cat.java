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
public class Cat extends CommonAnimal{
    @Override
    public void run(String name) {
        log.info("cat :{} is running", name);
    }
}
