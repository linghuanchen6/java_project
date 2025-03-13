package com.neuedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuedu.entity.Equipment;
import com.neuedu.entity.News;
import com.neuedu.entity.Server;
import com.neuedu.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper extends BaseMapper<News> {
}
