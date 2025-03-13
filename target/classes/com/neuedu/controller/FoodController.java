package com.neuedu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.Result;
import com.common.ServerResponse;
import com.neuedu.entity.Equipment;
import com.neuedu.entity.Food;
import com.neuedu.entity.News;
import com.neuedu.service.FoodService;
import com.neuedu.service.NewsService;
import com.neuedu.vo.EquipmentVo;
import com.neuedu.vo.FoodVo;
import com.neuedu.vo.NewsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api("美食管理")
@RestController
@RequestMapping("/food")
@CrossOrigin
public class FoodController {
    @Resource
    FoodService foodService;



    @ApiOperation("美食列表")
    @GetMapping("/list")
    public ServerResponse list(FoodVo food) {

        //调用业务方法
        Page<Food> foodPage = foodService.getFoodList(food);
        //返回分页对象
        return ServerResponse.success("").data("foodPage", foodPage);
    }

    @ApiOperation("用户获取美食列表")
    @GetMapping("/lists")
    public ServerResponse lists(String name, Integer currentPage, Integer pageSize) {
        FoodVo food = new FoodVo();
        food.setName(name);
        food.setCurrentPage(currentPage);
        food.setPageSize(pageSize);
        Page<Food> foodList = foodService.getFoodList(food);
        //返回分页对象
        return ServerResponse.success("").data("foodList", foodList);
    }
    @ApiOperation("美食新闻")
    @PostMapping("/add")
    public ServerResponse add(@RequestBody Food food) {

        if(StringUtils.isEmpty(food.getName()))  {
            return ServerResponse.error("名字不能为空");
        }
        if(foodService.save(food)){
            return ServerResponse.success("添加美食成功");
        }else{
            return ServerResponse.error("添加美食失败");
        }
    }

    @ApiOperation("根据编号获取美食信息")
    @GetMapping("/get/{id}")
    public ServerResponse get(@PathVariable int id) {

        Food food = foodService.getById(id);

        if(food!=null){
            return ServerResponse.success("").data("food", food);
        }else{
            return ServerResponse.error("获取美食信息失败");
        }
    }

    @ApiOperation("修改美食信息")
    @PostMapping("/modify")
    public ServerResponse modify(@RequestBody Food food) {

        if(StringUtils.isEmpty(food.getName()) && StringUtils.isEmpty(food.getId())
                && StringUtils.isEmpty(food.getContent()))  {
            return ServerResponse.error("所修改的美食信息不能为空");
        }

        if(foodService.updateById(food)){
            return ServerResponse.success("修改美食信息成功");
        }else{
            return ServerResponse.error("修改美食信息失败");
        }
    }
    @ApiOperation("批量删除美食")
    @DeleteMapping("/deleteBatch")
    public ServerResponse deleteBatch(String ids) {

        if(ids == ""){
            return ServerResponse.error("未选中要删除的美食列表");
        }
        if(foodService.deleteBatch(ids)){
            return ServerResponse.success("批量删除美食成功");
        }else{
            return ServerResponse.error("批量删除美食失败");
        }
    }
    @ApiOperation("删除美食")
    @DeleteMapping("/remove/{id}")
    public ServerResponse remove(@PathVariable int id) {

        if(foodService.removeById(id)){
            return ServerResponse.success("删除美食成功");
        }else{
            return ServerResponse.error("删除美食失败");
        }
    }
}