package com.neuedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuedu.entity.Equipment;
import com.neuedu.entity.Server;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ServerMapper  extends BaseMapper<Server> {
}
