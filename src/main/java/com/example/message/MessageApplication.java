package com.example.message;

import com.example.message.common.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@Slf4j
@MapperScan({"com.example.message.dao"})
@EnableCaching
@EnableAsync
public class MessageApplication {

//    @Autowired
//    RedisService redisService;



    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }

//    @RequestMapping("/test")
//    public String hello(){
//        redisService.set("test","123",1000000L);
//        return "redis test success";
//    }



}
