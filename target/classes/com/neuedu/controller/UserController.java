package com.neuedu.controller;

import com.common.ServerResponse;
import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Api("用户管理")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {


    @Resource
    UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ServerResponse login(@RequestBody User user, HttpSession session) {

        System.out.println(user);

        user = userService.login(user);

        System.out.println(user);

        if (user != null) {  //找到匹配的记录

            //在session属性范围中保存用户对象
            session.setAttribute("currentUser", user);

            System.out.println(user.getName());

            //返回
            return ServerResponse.success("登录成功")
                    .data("currentUser", user);
        } else {
            return ServerResponse.error("登录失败，用户名或密码错误");
        }

    }

    @ApiOperation("用户注销")
    @PostMapping("/logout")
    public ServerResponse logout(HttpSession session) {

        //从session属性范围中删除用户对象
        session.removeAttribute("currentUser");

        return ServerResponse.success("注销成功");
    }

}