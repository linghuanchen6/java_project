package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.Food;

import com.neuedu.mapper.FoodMapper;
import com.neuedu.service.FoodService;
import com.neuedu.vo.FoodVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {
    @Resource
    private FoodMapper foodMapper;

    /**
     * 获取所有news
     *
     * @return newsVoList
     */

    @Override
    public Page<Food> getFoodList(FoodVo food) {
        System.out.println(food);

        Page page = new Page(food.getCurrentPage(), food.getPageSize());

        QueryWrapper<Food> wrapper = new QueryWrapper<>();

        if(food.getName() != null){
            wrapper.like("name", food.getName());
        }

        if(food.getId() != null){
            wrapper.eq("id", food.getId());
        }


        return foodMapper.selectPage(page, wrapper);
    }

    @Override
    public boolean deleteBatch(String ids) {
        //将字符串拆分成数组  "1,2,3" --> [1,2,3]
        String[] arr = ids.split(",");

        //字符串数组转换集合  [1,2,3] --> {1,2,3}
        List<Integer> list = new ArrayList<>();

        for (String s : arr) {
            list.add(Integer.parseInt(s));
        }

        return this.removeByIds(list);
    }
}
