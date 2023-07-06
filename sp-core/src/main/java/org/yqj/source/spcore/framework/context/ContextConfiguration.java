package org.yqj.source.spcore.framework.context;

import org.springframework.context.annotation.Bean;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/12
 * Email: yaoqijunmail@foxmail.com
 */
//@Configuration
public class ContextConfiguration {

    @Bean(name = {"a", "b", "c", "d"})
    public ContextPerson personYao() {
        return new ContextPerson("1", "yao", 10);
    }

    @Bean
    public ContextPerson personQi() {
        return new ContextPerson("2", "qi", 20);
    }

    @Bean
    public ContextPerson personJun() {
        return new ContextPerson("3", "jun", 30);
    }
}
