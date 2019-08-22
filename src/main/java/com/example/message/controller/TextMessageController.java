package com.example.message.controller;

import com.example.message.common.MyRsp;
import com.example.message.service.TextMessageService;
import com.example.message.model.PhoneTokenParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @program: message-service
 * @description:
 * @author: BitCoc
 * @create: 2019-07-31 18:48
 **/
@RestController
@RequestMapping("textMessage")
@Api(value = "TextMessageController", description = "这是一个发手机验证码的接口文档")
public class TextMessageController {

    @Autowired
    TextMessageService textMessageService;


    /** 
     * @Description: 反解Token进行短信的发送
     * @Param: [phoneToken] 
     * @return: java.lang.Object 
     */
    @ApiOperation("反解Token进行短信的发送")
    @GetMapping("/sendCodeByPhone/{phoneToken}")
    public Object sendCodeByPhone(@PathVariable("phoneToken") String phoneToken)  {

        textMessageService.sendTextMessage(phoneToken);

        return MyRsp.success(null).msg("发送成功");
    }

    /** 
     * @Description: 验证短信验证码
     * @Param: [phoneToken] 
     * @return: java.lang.Object 
     */
    @ApiOperation("获取用户手机号和验证码信息和发送验证码进行匹配")
    @GetMapping("/verifyCodeByPhone/{phoneToken}/{code}")
    public Object verifyCodeByPhone(@PathVariable("phoneToken") String phoneToken,@PathVariable("code") String code)  {


        return textMessageService.verifyCodeByPhone(phoneToken,code)?MyRsp.success(null).msg("手机验证码验证成功"):MyRsp.error().msg("手机验证码验证失败");
    }

    /** 
    * @Description: 获取加密Token，包含手机号和模板的ID信息 
    * @Param: [phoneTokenParam] 
    * @return: java.lang.Object 
    */
    @ApiOperation("通过手机号和模板的ID获取加密Token")
    @PostMapping("/getPhoneToken")
    public Object getEmailToken(@RequestBody @Valid  PhoneTokenParam phoneTokenParam){

        String token = textMessageService.getPhoneToken(phoneTokenParam.getTemplateId(),phoneTokenParam.getPhone());
        return token==null?MyRsp.error().msg("Token生成失败。。。。"):MyRsp.success(token).msg("Token生成成功");
    }
}
