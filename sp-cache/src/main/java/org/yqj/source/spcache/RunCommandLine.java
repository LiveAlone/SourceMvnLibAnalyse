package org.yqj.source.spcache;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
    private PersonService personService;

    @Override
    public void run(String... args) throws Exception {
        log.info("starting command line ...");

        for (int i = 0; i < 10; i++) {
            CachePerson person = personService.gainCachePerson(100);
            log.info("command line get cache person is :{}", person);
        }
    }
}
