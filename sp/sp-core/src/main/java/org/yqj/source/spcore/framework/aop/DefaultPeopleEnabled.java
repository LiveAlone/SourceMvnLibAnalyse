package org.yqj.source.spcore.framework.aop;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/26
 * Email: yaoqijunmail@foxmail.com
 */
@Slf4j
public class DefaultPeopleEnabled implements PeopleEnabled{
    @Override
    public boolean enabled() {
        log.info("default enabled called");
        return true;
    }
}
