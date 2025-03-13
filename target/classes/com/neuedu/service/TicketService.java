package com.neuedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.Equipment;
import com.neuedu.entity.Server;
import com.neuedu.entity.Ticket;
import com.neuedu.entity.User;
import com.neuedu.vo.EquipmentVo;
import com.neuedu.vo.TicketVo;
import org.springframework.stereotype.Service;

@Service
public interface TicketService extends IService<Ticket> {

    boolean add(Ticket ticket);

    Page<Ticket> getTicketList(TicketVo ticket);

    boolean deleteBatch(String ids);

}