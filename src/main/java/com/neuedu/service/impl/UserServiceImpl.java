package com.neuedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.User;
import com.neuedu.mapper.UserMapper;
import com.neuedu.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Override
    public User login(User user) {
        List<User> list = this.query()
                .eq("openid", user.getOpenid())
                .eq("password", user.getPassword())
                .list();

        if(list.size() > 0) {
            return list.get(0);
        }else{
            return null;
        }

    }
}
