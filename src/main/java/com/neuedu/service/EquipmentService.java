package com.neuedu.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.Employ;
import com.neuedu.entity.Equipment;
import com.neuedu.entity.News;
import com.neuedu.vo.EquipmentVo;
import com.neuedu.vo.NewsVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipmentService extends IService<Equipment> {

    //获取设备列表
    Page<Equipment> getEquipmentList(EquipmentVo equipment);

    boolean deleteBatch(String ids);


}
