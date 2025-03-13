package com.neuedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.ServerResponse;
import com.neuedu.entity.Employ;
import com.neuedu.mapper.EmployMapper;
import com.neuedu.service.EmployService;
import com.neuedu.vo.EmployCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Api("员工管理")
@RestController
@RequestMapping("/employ")
@CrossOrigin
public class EmployController {


    @Resource
    EmployService employService;
    @Resource
    EmployMapper employMapper;

    @ApiOperation("员工数量")
    @PostMapping("/count")
    public ServerResponse count() {
        QueryWrapper<Employ> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        Integer count = employMapper.selectCount(queryWrapper);
        return ServerResponse.success("").data("count", count);
    }

    @ApiOperation("员工列表")
    @GetMapping("/list")
    public ServerResponse list(EmployCondition employCondition) {

        //调用业务方法
        Page<Employ> employPage = employService.getEmployList(employCondition);
        //返回分页对象
        return ServerResponse.success("").data("employPage", employPage);
    }

    @ApiOperation("添加员工")
    @PostMapping("/add")
    public ServerResponse add(@RequestBody Employ employ) {

        if (StringUtils.isEmpty(employ.getName())) {
            return ServerResponse.error("姓名不能为空");
        }
        if (StringUtils.isEmpty(employ.getUsername())) {
            return ServerResponse.error("用户名不能为空");
        }
        employ.setCreateTime(LocalDateTime.now());
        employ.setUpdateTime(LocalDateTime.now());
        if (employService.save(employ)) {
            return ServerResponse.success("添加用户成功");
        } else {
            return ServerResponse.error("添加用户失败");
        }
    }

    @ApiOperation("根据编号获取员工信息")
    @GetMapping("/get/{id}")
    public ServerResponse get(@PathVariable int id) {

        Employ employ = employService.getById(id);

        if (employ != null) {
            return ServerResponse.success("").data("employ", employ);
        } else {
            return ServerResponse.error("获取用户信息失败");
        }
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/modify")
    public ServerResponse modify(@RequestBody Employ employ) {

        if (StringUtils.isEmpty(employ.getName()) && StringUtils.isEmpty(employ.getUsername())
                && StringUtils.isEmpty(employ.getPassword()) && StringUtils.isEmpty(employ.getPhone())
                && StringUtils.isEmpty(employ.getSex()) && StringUtils.isEmpty(employ.getIdNumber())) {
            return ServerResponse.error("所修改的用户信息不能为空");
        }

        if (employService.updateById(employ)) {
            return ServerResponse.success("修改用户信息成功");
        } else {
            return ServerResponse.error("修改用户信息失败");
        }
    }

    @ApiOperation("批量删除用户")
    @DeleteMapping("/deleteBatch")
    public ServerResponse deleteBatch(String ids) {

        if (ids == "") {
            return ServerResponse.error("未选中要删除的用户列表");
        }
        if (employService.deleteBatch(ids)) {
            return ServerResponse.success("批量删除用户成功");
        } else {
            return ServerResponse.error("批量删除用户失败");
        }
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/remove/{id}")
    public ServerResponse remove(@PathVariable int id) {

        if (employService.removeById(id)) {
            return ServerResponse.success("删除用户成功");
        } else {
            return ServerResponse.error("删除用户失败");
        }
    }


}