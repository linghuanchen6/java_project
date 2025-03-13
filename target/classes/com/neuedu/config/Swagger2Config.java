package com.neuedu.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2    //开启Swagger2的功能
public class Swagger2Config {

    @Bean
    public Docket docket(){

        ApiInfo apiInfo = new ApiInfoBuilder().title("游乐园管理项目接口文档").build();   //替换掉页面标题Api Documentation

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo);
    }

}
