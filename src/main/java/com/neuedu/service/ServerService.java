package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.Equipment;
import com.neuedu.entity.Server;
import com.neuedu.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface ServerService extends IService<Server> {
    Server Slogin(Server server);

    boolean register(Server server);

}
