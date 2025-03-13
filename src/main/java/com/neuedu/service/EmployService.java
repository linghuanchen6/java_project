package com.neuedu.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.Employ;
import com.neuedu.entity.User;
import com.neuedu.vo.EmployCondition;
import org.springframework.stereotype.Service;

@Service
public interface EmployService extends IService<Employ> {

    //获取员工列表
    Page<Employ> getEmployList(EmployCondition employCondition);

    boolean deleteBatch(String ids);


}
