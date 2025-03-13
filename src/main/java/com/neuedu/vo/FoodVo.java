package com.neuedu.vo;

import lombok.Data;

@Data
public class FoodVo {
    private Integer id;
    private String name;
    private String content;

    private Integer currentPage;
    private Integer pageSize;
}
