package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.Server;
import com.neuedu.entity.User;
import com.neuedu.mapper.ServerMapper;
import com.neuedu.mapper.UserMapper;
import com.neuedu.service.ServerService;
import com.neuedu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ServerServiceImpl extends ServiceImpl<ServerMapper,Server> implements ServerService {

    @Resource
    private ServerMapper serverMapper;

    @Override
    public Server Slogin(Server server) {
        List<Server> list = this.query()
                .eq("username", server.getUsername())
                .eq("password", server.getPassword())
                .list();

        if(list.size() > 0) {
            list.get(0).setLog(1);
            serverMapper.updateById(list.get(0));
            return list.get(0);
        }else{
            return null;
        }

    }

    @Override
    public boolean register(Server server) {
        QueryWrapper<Server> wrapper = new QueryWrapper<>();
        wrapper.eq("username",server.getUsername());
        List<Server> servers = serverMapper.selectList(wrapper);
        if(servers.size() > 0){
            return  false;
        }

        return serverMapper.insert(server) == 1;

    }
}
