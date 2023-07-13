package org.yqj.source.spcore.framework.aop;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yqj.source.spcore.framework.aop.entity.AopUser;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2023/7/13
 * Email: yaoqijunmail@foxmail.com
 */
@Component
@Slf4j
public class AopCommandLine implements CommandLineRunner {

    @Resource
    private AopUserService aopUserService;

    @Override
    public void run(String... args) throws Exception {
        String saveResult = aopUserService.saveUser(new AopUser("yaoqijun", 18, 1000));
        log.info("save result:{}", saveResult);
    }
}
