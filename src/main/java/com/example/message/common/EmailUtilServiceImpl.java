package com.example.message.common;

import com.example.message.common.EmailUtilService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("EmailUtilService")
public class EmailUtilServiceImpl implements EmailUtilService {

    @Async
    @Override
    public void sendNormalText(String emailToken) {

        // 通过token获取邮件内容
        String text=JwtUtil.getContentfromEmailToken(emailToken);
        // 通过token获取邮件地址
        String emailAddress=JwtUtil.getEmailfromEmailToken(emailToken);
        // 通过token获取邮件标题
        String title = JwtUtil.getTitlefromEmailToken(emailToken);
        //Todo 调用EmailUtilService 发送邮件
        try {

            EmailUtil.sendEmail(text,emailAddress,title);
        } catch (Exception e) {
            e.printStackTrace();
//            throw new MyException(HttpCode.ERROR).msg("邮件发送失败");
        }
    }

    @Override
    public String getEmailToken(String email, String content,String title) {
        String privateKey="message";
        return JwtUtil.createEmailToken(email,content,privateKey,title);
    }
}
