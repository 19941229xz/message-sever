package com.example.message.common;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Api(value = "发邮件模块接口",description = "这是一个发邮件模块的接口文档")
@Slf4j
@RestController
@RequestMapping("emailSender")
public class EmailController {

    @GetMapping("/sendNormalEmail/{emailToken}")
    public Object sendNormalEmail(@PathVariable("emailToken") String emailToken){
        //Todo

        //Todo jwtUtil 验证并解码 emailToken 获取邮箱地址

        //Todo 调用EmailUtilService 发送邮件


        return MyRsp.success(null);
    }





}
