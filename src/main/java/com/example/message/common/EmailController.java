package com.example.message.common;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "发邮件模块接口",description = "这是一个发邮件模块的接口文档")
@Slf4j
@RestController
@RequestMapping("emailSender")
public class EmailController {

    @Autowired
    EmailUtilService emailUtilService;

    @GetMapping("/sendNormalEmail/{emailToken}")
    public Object sendNormalEmail(@PathVariable("emailToken") String emailToken){

        emailUtilService.sendNormalText(emailToken);
//        return emailUtilService.sendNormalText(text,emailAddress,title)?MyRsp.success(null):MyRsp.error().msg("发送失败，请重试(可能的原因，身份验证不通过！)");
        return MyRsp.success(null).msg("发送成功");
    }

    @GetMapping("/getEmailToken/{email}/{content}/{title}")
    public Object getEmailToken(@PathVariable("email") String email,@PathVariable("content") String content,@PathVariable("title") String title){

        String token = emailUtilService.getEmailToken(email,content,title);
        return token==null?MyRsp.error().msg("获取token失败，原因正在检查中。"):MyRsp.success(token);
    }



}
