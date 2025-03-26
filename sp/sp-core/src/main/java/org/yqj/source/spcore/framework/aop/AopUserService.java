package org.yqj.source.spcore.framework.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.yqj.source.spcore.framework.aop.entity.AopUser;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2022/1/26
 * Email: yaoqijunmail@foxmail.com
 */
//@Component
@Slf4j
public class AopUserService {

    public boolean validateUser(AopUser user) {
        log.info("validate user info user:{}", user);
        if (StringUtils.hasLength(user.getName())) {
            return true;
        }
        throw new IllegalArgumentException("nameNull");
    }

    public String saveUser(AopUser user) throws InterruptedException {
        int cost = ThreadLocalRandom.current().nextInt(1000);
        Thread.sleep(cost);
        log.info("save user success user:{}, cost:{}", user, cost);
        return "SUCCESS";
    }

    public AopUser queryUser(String name, int age, int salary) throws InterruptedException {
        int cost = ThreadLocalRandom.current().nextInt(1000);
        Thread.sleep(cost);
        AopUser aopUser = new AopUser(name, age, salary);
        log.info("query user success user:{}, cost:{}", aopUser, cost);
        return aopUser;
    }
}
