package org.yqj.source.spcore.framework.env;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/7/4
 * Email: yaoqijunmail@foxmail.com
 */
//@Component
@Slf4j
public class EnvCommandLine implements CommandLineRunner, EnvironmentAware {

    private Environment environment;

    @Resource
    private ConfigurationBean configurationBean;

    @Override
    public void run(String... args) throws Exception {
//        if (environment instanceof AbstractEnvironment) {
//            // 不同环境配置
//            MutablePropertySources mutablePropertySources = ((AbstractEnvironment) environment).getPropertySources();
//            mutablePropertySources.forEach(propertySource -> {
//                log.info("********** source {}, -- {}", propertySource.getName(), propertySource);
//            });
//        }

//        String appAlias = environment.getProperty("app.alias");
//        log.info("config app.alias is:{}", appAlias);

//        String valueFirst = environment.getProperty("config.value_first");
//        log.info("config value first is:{}", valueFirst);
//        log.info("config value first from bean is:{}", configurationBean.getValueFirst());

        String processorValue = environment.getProperty("processor.value");
        log.info("config processor value is :{}", processorValue);

        log.info("configuration bean info is :{}", configurationBean);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
