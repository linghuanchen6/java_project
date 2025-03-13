package com.neuedu.controller;

import com.common.Result;
import com.common.ServerResponse;
import com.neuedu.entity.Server;
import com.neuedu.service.ServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Api("用户管理")
@RestController
@RequestMapping("/server")
@CrossOrigin
public class ServerController {


    @Resource
    ServerService serverService;

    @PutMapping("/modify")
    public Result modify(@RequestBody Server server,HttpSession session){
        if(StringUtils.isEmpty(server.getUsername())){
            return Result.error("用户名不能为空");
        }
        if(StringUtils.isEmpty(server.getPhone())){
            return Result.error("手机号不能为空");
        }
        if(StringUtils.isEmpty(server.getEmail())){
            return Result.error("邮箱不能为空");
        }if(StringUtils.isEmpty(server.getName())){
            return Result.error("昵称不能为空");
        }
        serverService.updateById(server);
        session.setAttribute("currentUser",server);
        return Result.success("修改成功");
    }

    @ApiOperation("获取信息")
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Integer id){
        Server server = serverService.getById(id);
        return Result.success("").data("server",server);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ServerResponse Slogin(@RequestBody Server server, HttpSession session) {

        System.out.println(server);

        server = serverService.Slogin(server);

        System.out.println(server);

        if (server != null) {  //找到匹配的记录

            //在session属性范围中保存用户对象
            session.setAttribute("currentUser", server);

            System.out.println(server.getUsername());

            //返回
            return ServerResponse.success("登录成功")
                    .data("currentUser", server);
        } else {
            return ServerResponse.error("登录失败，用户名或密码错误");
        }

    }
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result Register(@RequestBody Server server) {

            boolean register = serverService.register(server);
            if(register){
                return Result.success("注册成功");
            }

            return Result.error("注册失败，用户名已重复");
    }


    @ApiOperation("用户注销")
    @PostMapping("/logout")
    public ServerResponse logout(HttpSession session) {

        //从session属性范围中删除用户对象
        session.removeAttribute("currentUser");

        return ServerResponse.success("注销成功");
    }

}