package org.yqj.source.orm.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.yqj.source.orm.mapper.User;

public interface UserService extends IService<User> {

    @DS("users")
    User getUserById(Long id);

    @DS("orders")
    void insertUser(User user);

}
