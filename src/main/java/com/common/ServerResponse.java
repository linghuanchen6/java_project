package com.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//公共统一返回类
@Data
public class ServerResponse {
    private Integer status;   //操作状态 1：成功  0：失败
    private String message;   //提示信息
    private Map<String, Object> data = new HashMap<>();  //返回的数据

    //构造方法私有化
    private ServerResponse(){
    }

    //操作成功的静态方法
    public static ServerResponse success(String message){
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(Constants.SUCCESS);
        serverResponse.setMessage(message);
        return serverResponse;
    }

    //操作成功时，添加数据
    public ServerResponse data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    //操作失败的静态方法
    public static ServerResponse error(String message){
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(Constants.ERROR);
        serverResponse.setMessage(message);
        return serverResponse;
    }

}
