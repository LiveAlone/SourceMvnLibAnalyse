package org.yqj.source.orm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
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
        testDsChange();

//        testFillField();

//        testPageQuery();
    }

    private void testPageQuery() {
//        for (int i = 0; i < 100; i++) {
//            User user = new User();
//            user.setName(String.format("userName-%d", i));
//            user.setAge(1);
//            user.setEmail(String.format("userEmail-%d", i));
//            userService.save(user);
//        }

        Page<User> pageQuery = new Page<>(1, 10);
        IPage<User> userIPage = userService.page(pageQuery, new LambdaQueryWrapper<User>().orderByDesc(User::getId));
        log.info("page user is :{}", userIPage.getTotal());
        log.info("page user list is :{}", userIPage.getRecords());
    }

    private void testFillField() throws InterruptedException {
//        User user = new User();
//        user.setName("testInsert");
//        user.setAge(100);
//        user.setEmail("email_insert");
//        userService.save(user);
//
//        Thread.sleep(5000);
//        User updateUser = new User();
//        updateUser.setName("testUpdate");
//        updateUser.setAge(121);
//        updateUser.setEmail("email_update");
//        updateUser.setId(user.getId());
//        userService.updateById(updateUser);

        userService.removeById(1L);
        log.info("user is :{}", userService.getById(2L));
    }

    /**
     * 测试数据源切换
     */
    private void testDsChange() {
        User user = new User();
        user.setName("testInsert");
        user.setAge(100);
        user.setEmail("email_insert");
        user.setCourse(Lists.newArrayList("math", "english"));

        userService.save(user);
        log.info("user is :{}", userService.getById(1L));

//        userService.insertUserToOrders(user);
//        log.info("uu is :{}", userService.getUserByIdFromUsers(1L));
    }
}
