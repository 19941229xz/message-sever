package com.example.message.controller;

import com.example.message.common.MyRsp;
import com.example.message.service.EmailUtilService;
import com.example.message.model.EmailCodeTokenParam;
import com.example.message.model.EmailTokenParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(value = "发邮件模块接口",description = "这是一个发邮件模块的接口文档")
@Slf4j
@RestController
@RequestMapping("emailSender")
public class EmailController {

    @Autowired
    EmailUtilService emailUtilService;


    @ApiModelProperty("获取发送普通email的接口")
    @GetMapping("/sendNormalEmail/{emailToken}")
    public Object sendNormalEmail(@PathVariable("emailToken") String emailToken){

        emailUtilService.sendNormalText(emailToken);
//        return emailUtilService.sendNormalText(text,emailAddress,title)?MyRsp.success(null):MyRsp.error().msg("发送失败，请重试(可能的原因，身份验证不通过！)");
        return MyRsp.success(null).msg("发送成功");
    }

    @ApiModelProperty("普通邮件获取emailToken的接口")
    @PostMapping("/getEmailToken")
    public Object getEmailToken(@RequestBody @Valid EmailTokenParam emailTokenParam){

        String token = emailUtilService.getEmailToken(emailTokenParam.getEmailAdress(),emailTokenParam.getText(),emailTokenParam.getTitle());
        return token==null?MyRsp.error().msg("获取token失败，原因正在检查中。"):MyRsp.success(token);
    }

    @ApiModelProperty("获取emailCodeToken的接口")
    @PostMapping("/getEmailCodeToken")
    public Object getEmailCodeToken(@RequestBody @Valid EmailCodeTokenParam emailCodeTokenParam){
        String token = emailUtilService.getEmailCodeToken(emailCodeTokenParam);
        return token==null?MyRsp.error().msg("获取token失败，原因正在检查中。"):MyRsp.success(token);
    }


    @ApiModelProperty("发送emailCodeToken的接口")
    @GetMapping("/sendEmailCode/{emailCodeToken}")
    public Object sendEmailCode(@PathVariable("emailCodeToken") String emailCodeToken){

        emailUtilService.sendEmailCode(emailCodeToken);
        return MyRsp.success(null).msg("发送成功");
    }

    @ApiModelProperty("验证code的接口")
    @GetMapping("/verifyEmailCode/{emailCodeToken}/{code}")
    public Object verifyEmailCode(@PathVariable("emailCodeToken") String emailCodeToken,@PathVariable("code") String code){

        return emailUtilService.verifyEmailCode(emailCodeToken,code)?MyRsp.success(null).msg("验证成功"):MyRsp.error().msg("验证失败");
    }



}
