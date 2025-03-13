
package com.common;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//公共统一返回类
@Data
public class Result {
    private Integer status;   //操作状态 1：成功  0：失败
    private String message;   //提示信息
    private Map<String, Object> data = new HashMap<>();  //返回的数据

    //构造方法私有化
    private Result(){
    }

    //操作成功的静态方法
    public static Result success(String message){
        Result result = new Result();
        result.setStatus(Constants.SUCCESS);
        result.setMessage(message);
        return result;
    }

    //操作成功时，添加数据
    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    //操作失败的静态方法
    public static Result error(String message){
        Result result = new Result();

        result.setStatus(Constants.ERROR);
        result.setMessage(message);
        return result;
    }

}
