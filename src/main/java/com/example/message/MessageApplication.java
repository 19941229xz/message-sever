package com.example.message;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
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
