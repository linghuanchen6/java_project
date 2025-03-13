package com.neuedu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketVo {
    @TableId(type = IdType.INPUT)  //INPUT表示由程序生成主键值
    private Integer id;
    private String category;
    private String price;
    private String memo;
    private String name;
    private String idNumber;
    private int status;

    @JsonFormat(pattern = "yyyy-MM-dd")   //前台发送的yyyy-MM-dd的字符串自动转换成日期型
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")   //前台发送的yyyy-MM-dd的字符串自动转换成日期型
    private LocalDateTime updateTime;

    private Integer currentPage;
    private Integer pageSize;

}