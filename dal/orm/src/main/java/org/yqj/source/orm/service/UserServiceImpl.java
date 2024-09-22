package org.yqj.source.orm.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;
import org.yqj.source.orm.mapper.User;
import org.yqj.source.orm.mapper.UserMapper;

/**
 * Description:
 *
 * @author yaoqijun
 * @date 2024/4/18
 * Email: yaoqijunmail@foxmail.com
 */
@Component
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getUserByIdFromUsers(Long id) {
        return this.getBaseMapper().selectById(id);
    }

    @Override
    public void insertUserToOrders(User user) {
        this.getBaseMapper().insert(user);
    }
}
