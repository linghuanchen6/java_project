package com.neuedu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(type = IdType.INPUT)  //INPUT表示由程序生成主键值
    private int id;

    private String openid;


    private String name;

    private String phone;
    //private String registerDate;
    private String sex;

    private String idNumber;

    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd")   //前台发送的yyyy-MM-dd的字符串自动转换成日期型
    private Date createTime;
    private String password;


}

