package com.neuedu.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//字符串工具类
public class StringUtil {

    //生成新的主文件名改为自定义的格式 yyyyMMddHHmmssSSSXXX
    public static String generateFilename(){

        //将当前时间转换为yyyyMMddHHmmssSSS的字符串
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String now = df.format(new Date());

        //产生100到999之间的三位随机数
        Random random = new Random();
        int number = random.nextInt(900) + 100;

        return now + number;

    }

    public static void main(String[] args) {

        System.out.println(generateFilename());
        System.out.println(generateFilename());
        System.out.println(generateFilename());
    }
}
