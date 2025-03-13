package com.neuedu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {

    @TableId(type = IdType.INPUT)  //INPUT表示由程序生成主键值
    private Integer id;
    private String name;
    private String content;
    private int status;

    @JsonFormat(pattern = "yyyy-MM-dd")   //前台发送的yyyy-MM-dd的字符串自动转换成日期型
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")   //前台发送的yyyy-MM-dd的字符串自动转换成日期型
    private LocalDateTime updateTime;
}