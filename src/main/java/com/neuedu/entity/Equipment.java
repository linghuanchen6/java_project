package com.neuedu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.neuedu.vo.BaseCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {

    @TableId(value = "id",type = IdType.INPUT)  //INPUT表示由程序生成主键值
    private Integer id;

    private String name;

    private String location;
    //private String registerDate;
    private int status;

    private String repair;
}