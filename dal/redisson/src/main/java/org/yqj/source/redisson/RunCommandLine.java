package org.yqj.source.redisson;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/6/6
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class RunCommandLine implements CommandLineRunner {

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        List<String> names = Arrays.asList(applicationContext.getBeanDefinitionNames());
        log.info("starting command line ... {}", names);
    }
}
