package com.example.message;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
@Slf4j
@MapperScan({"com.example.message.dao"})
@EnableCaching
@EnableAsync
public class MessageApplication {


    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }

}