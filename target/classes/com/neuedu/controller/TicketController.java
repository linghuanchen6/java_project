package com.neuedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.Result;
import com.common.ServerResponse;
import com.neuedu.entity.Equipment;
import com.neuedu.entity.News;
import com.neuedu.entity.Ticket;
import com.neuedu.mapper.NewsMapper;
import com.neuedu.mapper.TicketMapper;
import com.neuedu.service.TicketService;
import com.neuedu.vo.EquipmentVo;
import com.neuedu.vo.TicketVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api("用户管理")
@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class TicketController {


    @Resource
    TicketService ticketService;

    @Resource
    TicketMapper ticketMapper;

    @ApiOperation("新闻数量")
    @PostMapping("/count")
    public ServerResponse count() {
        QueryWrapper<Ticket> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        Integer count = ticketMapper.selectCount(queryWrapper);
        return ServerResponse.success("").data("count", count);
    }

    @ApiOperation("设备列表")
    @GetMapping("/list")
    public ServerResponse list(TicketVo ticket) {

        //调用业务方法
        Page<Ticket> ticketPage = ticketService.getTicketList(ticket);
        //返回分页对象
        return ServerResponse.success("").data("ticketPage", ticketPage);
    }


    @ApiOperation("获取信息")
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Integer id){
        Ticket ticket = ticketService.getById(id);
        return Result.success("获取成功").data("ticket",ticket);
    }

    @ApiOperation("批量删除设备")
    @DeleteMapping("/deleteBatch")
    public ServerResponse deleteBatch(String ids) {

        if(ids == ""){
            return ServerResponse.error("未选中要删除的设备列表");
        }
        if(ticketService.deleteBatch(ids)){
            return ServerResponse.success("批量删除设备成功");
        }else{
            return ServerResponse.error("批量删除设备失败");
        }
    }

    @ApiOperation("删除设备")
    @DeleteMapping("/remove/{id}")
    public ServerResponse remove(@PathVariable int id) {

        if(ticketService.removeById(id)){
            return ServerResponse.success("删除用户成功");
        }else{
            return ServerResponse.error("删除用户失败");
        }
    }

    @ApiOperation("添加购票信息")
    @PostMapping("/add")
    public Result add(@RequestBody Ticket ticket){
        if(ticketService.add(ticket)){
            return Result.success("购票成功");
        }
        return Result.error("购票失败");
    }

}