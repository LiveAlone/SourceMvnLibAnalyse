package org.yqj.source.orm;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yqj.source.orm.mapper.User;
import org.yqj.source.orm.service.UserService;

/**
 * @author 10126730
 * Date: 2024/6/11 17:50
 * Description:
 */
@Component
@Slf4j
public class ORMRunner implements CommandLineRunner {

    @Resource
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("testInsert");
        user.setAge(100);
        user.setEmail("email_insert");

        userService.insertUser(user);
        log.info("uu is :{}", userService.getUserById(1L));
    }
}
