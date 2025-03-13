package com.neuedu.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.Employ;
import com.neuedu.entity.Equipment;
import com.neuedu.entity.News;
import com.neuedu.entity.User;
import com.neuedu.vo.EmployCondition;
import com.neuedu.vo.EquipmentVo;
import com.neuedu.vo.NewsVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService extends IService<News> {

    /**
     * 获取所有news
     *
     * @return newsVoList
     */
    List<NewsVo> getAllNews();

    Page<News> getNewList(NewsVo news);

    boolean deleteBatch(String ids);
}