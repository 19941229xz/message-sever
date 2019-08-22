package com.example.message.service;

import com.example.message.model.EmailCodeTokenParam;

/**
 * @author xiongzh
 * @date 2019.7.31
 * @apiNote 邮箱工具类接口
 */
public interface EmailUtilService {


    /**
     * @apiNote 给邮箱发送普通文本内容
     * @param text 发送的内容
     * @param emailAddress 发送的目标邮箱
     * @return
     */
    void sendNormalText(String emailToken);

    String getEmailToken(String email,String content,String title);


    void sendEmailCode(String emailCodeToken);

    String getEmailCodeToken(EmailCodeTokenParam emailCodeTokenParam);
    boolean verifyEmailCode(String emailCodeToken,String code);
}
