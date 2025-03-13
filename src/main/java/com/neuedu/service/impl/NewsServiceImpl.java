package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.Equipment;
import com.neuedu.entity.News;
import com.neuedu.mapper.NewsMapper;
import com.neuedu.service.NewsService;
import com.neuedu.vo.NewsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {
    @Resource
    private NewsMapper newsMapper;

    /**
     * 获取所有news
     *
     * @return newsVoList
     */
    @Override
    public List<NewsVo> getAllNews() {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();

        List<News> newsList = newsMapper.selectList(queryWrapper);
        List<NewsVo> newsVoList = new ArrayList<>();
        for (News news1 : newsList) {
            newsVoList.add(getNews(news1));
        }
        return newsVoList;
    }

    @Override
    public Page<News> getNewList(NewsVo news) {
        System.out.println(news);

        Page page = new Page(news.getCurrentPage(), news.getPageSize());

        QueryWrapper<News> wrapper = new QueryWrapper<>();

        if(news.getTitle() != null){
            wrapper.like("title", news.getTitle());
        }

        if(news.getId() != null){
            wrapper.eq("id", news.getId());
        }


        return newsMapper.selectPage(page, wrapper);
    }

    @Override
    public boolean deleteBatch(String ids) {
        //将字符串拆分成数组  "1,2,3" --> [1,2,3]
        String[] arr = ids.split(",");

        //字符串数组转换集合  [1,2,3] --> {1,2,3}
        List<Integer> list = new ArrayList<>();

        for (String s : arr) {
            list.add(Integer.parseInt(s));
        }

        return this.removeByIds(list);
    }

    public NewsVo getNews(News news) {
        NewsVo newsVo = new NewsVo();
        newsVo.setId(news.getId());
        newsVo.setTitle(news.getTitle());
        newsVo.setAbstracts(news.getAbstracts());
        newsVo.setContent(news.getContent());
        return newsVo;
    }
}
