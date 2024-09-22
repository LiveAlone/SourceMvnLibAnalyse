package org.yqj.source.redisson;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.yqj.source.redisson.entity.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @Resource
    private Redisson redisson;

    @Override
    public void run(String... args) throws Exception {
        // 1 redisson 获取 bean
//        List<String> names = Arrays.asList(applicationContext.getBeanDefinitionNames());
//        names.forEach(name -> {
//            if (name.contains("edis") || name.contains("disson")) {
//                log.info("bean name is {}", name);
//            }
//        });
//        log.info("starting command line ... {}", names);

        Map<String, Object> mp = redisson.getMap("yqj-hash-redisson");
        mp.put("y", new Person("y", 1));
        mp.put("q", new Person("q", 2));
        mp.put("j", new Person("j", 3));
        mp.forEach((k,v) -> {
            log.info("key is {}, value is {}", k, v);
        });
    }
}
