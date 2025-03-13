package com.neuedu.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.ServerResponse;
import com.neuedu.entity.Employ;
import com.neuedu.entity.Equipment;
import com.neuedu.mapper.EmployMapper;
import com.neuedu.mapper.EquipmentMapper;
import com.neuedu.service.EquipmentService;
import com.neuedu.vo.EquipmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api("设备管理")
@RestController
@RequestMapping("/equipment")
@CrossOrigin
public class EquipmentController {


    @Resource
    EquipmentService equipmentService; //设备
    @Resource
    EquipmentMapper equipmentMapper;


    @ApiOperation("设施数量")
    @PostMapping("/count")
    public ServerResponse count() {
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        Integer count = equipmentMapper.selectCount(queryWrapper);
        return ServerResponse.success("").data("count", count);
    }

    @ApiOperation("设备列表")
    @GetMapping("/list")
    public ServerResponse list(EquipmentVo equipment) {

        //调用业务方法
        Page<Equipment> equipmentPage = equipmentService.getEquipmentList(equipment);
        //返回分页对象
        return ServerResponse.success("").data("equipmentPage", equipmentPage);
    }

    @ApiOperation("用户获取设备列表")
    @GetMapping("/lists")
    public ServerResponse lists(String name, Integer currentPage, Integer pageSize) {
        EquipmentVo equipment = new EquipmentVo();
        equipment.setName(name);
        equipment.setCurrentPage(currentPage);
        equipment.setPageSize(pageSize);
        Page<Equipment> equipmentList = equipmentService.getEquipmentList(equipment);
        //返回分页对象
        return ServerResponse.success("").data("equipmentList", equipmentList);
    }

    @ApiOperation("添加设备")
    @PostMapping("/add")
    public ServerResponse add(@RequestBody Equipment equipment) {

        if (StringUtils.isEmpty(equipment.getName())) {
            return ServerResponse.error("设备名不能为空");
        }
        if (equipmentService.save(equipment)) {
            return ServerResponse.success("添加用户成功");
        } else {
            return ServerResponse.error("添加用户失败");
        }
    }

    @ApiOperation("根据编号获取设备信息")
    @GetMapping("/get/{id}")
    public ServerResponse get(@PathVariable int id) {

        Equipment equipment = equipmentService.getById(id);

        if (equipment != null) {
            return ServerResponse.success("").data("equipment", equipment);
        } else {
            return ServerResponse.error("获取用户信息失败");
        }
    }

    @ApiOperation("修改设备信息")
    @PostMapping("/modify")
    public ServerResponse modify(@RequestBody Equipment equipment) {

        if (StringUtils.isEmpty(equipment.getName()) && StringUtils.isEmpty(equipment.getId())
                && StringUtils.isEmpty(equipment.getLocation()) && StringUtils.isEmpty(equipment.getRepair())) {
            return ServerResponse.error("所修改的设备信息不能为空");
        }

        if (equipmentService.updateById(equipment)) {
            return ServerResponse.success("修改设备信息成功");
        } else {
            return ServerResponse.error("修改设备信息失败");
        }
    }

    @ApiOperation("批量删除设备")
    @DeleteMapping("/deleteBatch")
    public ServerResponse deleteBatch(String ids) {

        if (ids == "") {
            return ServerResponse.error("未选中要删除的设备列表");
        }
        if (equipmentService.deleteBatch(ids)) {
            return ServerResponse.success("批量删除设备成功");
        } else {
            return ServerResponse.error("批量删除设备失败");
        }
    }

    @ApiOperation("删除设备")
    @DeleteMapping("/remove/{id}")
    public ServerResponse remove(@PathVariable int id) {

        if (equipmentService.removeById(id)) {
            return ServerResponse.success("删除用户成功");
        } else {
            return ServerResponse.error("删除用户失败");
        }
    }


}