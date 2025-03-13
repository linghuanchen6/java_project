package com.neuedu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class EquipmentVo {
    @TableId(value = "id",type = IdType.INPUT)  //INPUT表示由程序生成主键值
    private Integer id;

    private String name;

    private String location;
    //private String registerDate;
    private int status;

    private String repair;

    private Integer currentPage;
    private Integer pageSize;

}