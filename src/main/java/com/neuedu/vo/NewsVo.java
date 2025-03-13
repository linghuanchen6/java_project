package com.neuedu.vo;

import lombok.Data;

@Data
public class NewsVo {
    private Integer id;
    private String title;
    private String abstracts;
    private String content;

    private Integer currentPage;
    private Integer pageSize;
}
