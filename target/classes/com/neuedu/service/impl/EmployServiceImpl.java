package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.Employ;
import com.neuedu.entity.User;
import com.neuedu.mapper.EmployMapper;
import com.neuedu.mapper.UserMapper;
import com.neuedu.service.EmployService;
import com.neuedu.vo.EmployCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployServiceImpl extends ServiceImpl<EmployMapper, Employ> implements EmployService {

    @Resource
    EmployMapper employMapper;

    @Override
    public Page<Employ> getEmployList(EmployCondition employCondition) {
            ;
        System.out.println(employCondition);

        Page page = new Page(employCondition.getCurrentPage(), employCondition.getPageSize());

        QueryWrapper<Employ> wrapper = new QueryWrapper<>();

        wrapper.like("name", employCondition.getName());
        wrapper.like("username", employCondition.getUsername());


        return employMapper.selectPage(page, wrapper);
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
