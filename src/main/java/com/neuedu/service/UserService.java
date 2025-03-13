package com.neuedu.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {
    //登录
    User login(User user);
}
