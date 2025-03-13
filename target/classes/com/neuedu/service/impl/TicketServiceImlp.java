package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.Server;
import com.neuedu.entity.Ticket;
import com.neuedu.mapper.ServerMapper;
import com.neuedu.mapper.TicketMapper;
import com.neuedu.service.TicketService;
import com.neuedu.vo.TicketVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImlp extends ServiceImpl<TicketMapper, Ticket> implements TicketService {

    @Resource
    private TicketMapper ticketMapper;

    @Override
    public boolean add(Ticket ticket) {
        return ticketMapper.insert(ticket) == 1;
    }

    @Override
    public Page<Ticket> getTicketList(TicketVo ticket) {
        System.out.println(ticket);

        Page page = new Page(ticket.getCurrentPage(), ticket.getPageSize());

        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();

        if(ticket.getName() != null){
            wrapper.like("name", ticket.getName());
        }

        if(ticket.getId() != null){
            wrapper.eq("id", ticket.getId());
        }


        return ticketMapper.selectPage(page, wrapper);
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
}
