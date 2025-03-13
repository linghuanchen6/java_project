package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.Equipment;
import com.neuedu.mapper.EquipmentMapper;
import com.neuedu.service.EquipmentService;
import com.neuedu.vo.EquipmentVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {

    @Resource
    EquipmentMapper equipmentMapper;


    @Override
    public Page<Equipment> getEquipmentList(EquipmentVo equipment) {
        System.out.println(equipment);

        Page page = new Page(equipment.getCurrentPage(), equipment.getPageSize());

        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();

        if(equipment.getName() != null){
            wrapper.like("name", equipment.getName());
        }

        if(equipment.getId() != null){
            wrapper.eq("id", equipment.getId());
        }
        return equipmentMapper.selectPage(page, wrapper);
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
