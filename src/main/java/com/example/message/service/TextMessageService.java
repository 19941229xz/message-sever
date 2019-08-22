package com.example.message.service;

import org.mapstruct.Mapper;

/**
 * @apiNote 短信业务类
 * @author xiongzh
 * @date 2019.7.31
 */
@Mapper
public interface TextMessageService {


    /** 
     * @Description: 手机加密信息 
     * @Param: [phoneToken] 
     * @return: void 
     */
    public void sendTextMessage(String phoneToken);



    /**
     * @apiNote 给手机发送验证码
     * @param templateId  阿里云短信模板id
     * @param phone 目标手机号
     * @return
     */
    public String getPhoneToken(int templateId,String phone);



    public boolean verifyCodeByPhone(String phoneToken, String code);


}
