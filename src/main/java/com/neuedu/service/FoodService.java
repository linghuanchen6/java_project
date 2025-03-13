package com.neuedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.*;
import com.neuedu.vo.FoodVo;
import org.springframework.stereotype.Service;

@Service
public interface FoodService extends IService<Food> {

    /**
     * 获取所有news
     *
     * @return newsVoList
     */

    Page<Food> getFoodList(FoodVo food);

    boolean deleteBatch(String ids);
}