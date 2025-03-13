package com.neuedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan(basePackages = "com.neuedu.mapper")
public class ApartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApartApplication.class, args);
    }
}
