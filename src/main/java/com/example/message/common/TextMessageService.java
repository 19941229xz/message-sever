package com.example.message.common;

/**
 * @apiNote 短信业务类
 * @author xiongzh
 * @date 2019.7.31
 */
public interface TextMessageService {


    /**
     * @apiNote 给手机发送验证码
     * @param templateId  阿里云短信模板id
     * @param code  验证码4-6位数
     * @param phone 目标手机号
     * @return
     */
    public boolean sendTextMessage(String templateId,String code,String phone);


}
