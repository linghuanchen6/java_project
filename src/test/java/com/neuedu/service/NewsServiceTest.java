package com.neuedu.service;


import com.neuedu.vo.NewsVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class NewsServiceTest {

    @Resource
    NewsService newsService;

    @Test
    void testGetAllNews() {
        List<NewsVo> allNews = newsService.getAllNews();
        for (NewsVo allNew : allNews) {
            System.out.println(allNew);
        }
    }
}