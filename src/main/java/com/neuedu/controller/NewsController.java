package com.neuedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.Result;
import com.common.ServerResponse;
import com.neuedu.entity.Equipment;
import com.neuedu.entity.News;
import com.neuedu.mapper.EquipmentMapper;
import com.neuedu.mapper.NewsMapper;
import com.neuedu.service.NewsService;
import com.neuedu.vo.EquipmentVo;
import com.neuedu.vo.NewsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api("设备管理")
@RestController
@RequestMapping("/news")
@CrossOrigin
public class NewsController {
    @Resource
    NewsService newsService;
    @Resource
    NewsMapper newsMapper;

    @ApiOperation("新闻数量")
    @PostMapping("/count")
    public ServerResponse count() {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.select();
        Integer count = newsMapper.selectCount(queryWrapper);
        return ServerResponse.success("").data("count", count);
    }


    @ApiOperation("新闻列表")
    @GetMapping("/list")
    public Result getAllNews(){
        List<NewsVo> newsVoList = newsService.getAllNews();

        return Result.success("ok").data("newsVoList",newsVoList);
    }
    @ApiOperation("新闻列表")
    @GetMapping("/lists")
    public ServerResponse list(NewsVo news) {

        //调用业务方法
        Page<News> newsPage = newsService.getNewList(news);
        //返回分页对象
        return ServerResponse.success("").data("newsPage", newsPage);
    }
    @ApiOperation("添加新闻")
    @PostMapping("/add")
    public ServerResponse add(@RequestBody News news) {

        if(StringUtils.isEmpty(news.getTitle()))  {
            return ServerResponse.error("标题不能为空");
        }
        if(newsService.save(news)){
            return ServerResponse.success("添加新闻成功");
        }else{
            return ServerResponse.error("添加新闻失败");
        }
    }

    @ApiOperation("根据编号获取新闻信息")
    @GetMapping("/get/{id}")
    public ServerResponse get(@PathVariable int id) {

        News news = newsService.getById(id);

        if(news!=null){
            return ServerResponse.success("").data("news", news);
        }else{
            return ServerResponse.error("获取新闻信息失败");
        }
    }

    @ApiOperation("修改新闻信息")
    @PostMapping("/modify")
    public ServerResponse modify(@RequestBody News news) {

        if(StringUtils.isEmpty(news.getTitle()) && StringUtils.isEmpty(news.getId())
                && StringUtils.isEmpty(news.getAbstracts()) && StringUtils.isEmpty(news.getContent()))  {
            return ServerResponse.error("所修改的新闻信息不能为空");
        }

        if(newsService.updateById(news)){
            return ServerResponse.success("修改新闻信息成功");
        }else{
            return ServerResponse.error("修改新闻信息失败");
        }
    }
    @ApiOperation("批量删除新闻")
    @DeleteMapping("/deleteBatch")
    public ServerResponse deleteBatch(String ids) {

        if(ids == ""){
            return ServerResponse.error("未选中要删除的新闻列表");
        }
        if(newsService.deleteBatch(ids)){
            return ServerResponse.success("批量删除新闻成功");
        }else{
            return ServerResponse.error("批量删除新闻失败");
        }
    }
    @ApiOperation("删除新闻")
    @DeleteMapping("/remove/{id}")
    public ServerResponse remove(@PathVariable int id) {

        if(newsService.removeById(id)){
            return ServerResponse.success("删除新闻成功");
        }else{
            return ServerResponse.error("删除新闻失败");
        }
    }
}