package com.example.message.service.serviceImp;

import com.example.message.common.EmailUtil;
import com.example.message.common.HttpCode;
import com.example.message.common.JwtUtil;
import com.example.message.common.MyException;
import com.example.message.model.EmailCodeTokenParam;
import com.example.message.service.EmailAddressManagementService;
import com.example.message.service.EmailUtilService;
import com.example.message.service.PrivatekeyService;
import com.example.message.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("EmailUtilService")
public class EmailUtilServiceImpl implements EmailUtilService {

    @Autowired
    PrivatekeyService privatekeyService;

    @Autowired
    RedisService redisService;

    @Autowired
    EmailAddressManagementService emailAddressManagementService;

    @Async
    @Override
    public void sendNormalText(String emailToken) {

        // 通过token获取邮件内容
        String text= JwtUtil.getContentfromEmailToken(emailToken);
        // 通过token获取邮件地址
        String emailAddress=JwtUtil.getEmailfromEmailToken(emailToken);
        // 通过token获取邮件标题
        String title = JwtUtil.getTitlefromEmailToken(emailToken);

        String email=emailAddressManagementService.getEmail().getEmailAddress();
        String shouquanma=emailAddressManagementService.getEmail().getAuthorizationCode();
        //Todo 调用EmailUtilService 发送邮件
        try {

            EmailUtil.sendEmail(text,emailAddress,title,email,shouquanma);
        } catch (Exception e) {
            e.printStackTrace();
//            throw new MyException(HttpCode.ERROR).msg("邮件发送失败");
        }
    }

    @Override
    public String getEmailToken(String email, String content,String title) {

        String privateKey = privatekeyService.getPrivatekeyById(1).getPrivatekey();

        return JwtUtil.createEmailToken(email,content,privateKey,title);
    }

    @Override
    @Async
    public void sendEmailCode(String emailCodeToken) {
        // 通过token获取邮件code验证码
        String code = JwtUtil.getCodefromEmailToken(emailCodeToken);
        System.out.println(code);
        // 通过token获取邮件内容
        String text="你的验证码为："+code;
        // 通过token获取邮件地址
        String emailAddress=JwtUtil.getEmailfromEmailToken(emailCodeToken);
        // 通过token获取邮件标题
        String title = "验证码邮件";
        //从emailAddressManagementService中定义的方法随机取出一个系统email和授权码
        String email=emailAddressManagementService.getEmail().getEmailAddress();
        String shouquanma=emailAddressManagementService.getEmail().getAuthorizationCode();
        //Todo 调用EmailUtilService 发送邮件
        try {

            EmailUtil.sendEmail(text,emailAddress,title,email,shouquanma);
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    //获取EmailCodeToken
    @Override
    public String getEmailCodeToken(EmailCodeTokenParam emailCodeTokenParam) {
        String code = String.valueOf(new Random().nextInt(8999) + 1000);
        System.out.println(code);
        String privateKey = privatekeyService.getPrivatekeyById(1).getPrivatekey();
        //将验证码保存到redis中
        redisService.set(emailCodeTokenParam.getEmailAdress(),code);
        return JwtUtil.createEmailCodeToken(emailCodeTokenParam.getEmailAdress(),privateKey,code);
    }

    @Override
    public boolean verifyEmailCode(String emailCodeToken, String code) {
        // correctCode 正确的验证码
        String correctCode = "";
        if(redisService.exists(JwtUtil.getEmailfromEmailCodeToken(emailCodeToken))){
           correctCode = redisService.get(JwtUtil.getEmailfromEmailCodeToken(emailCodeToken)).toString();
        }else {
            throw new MyException(HttpCode.ERROR).msg("验证码已过期");
        }
        if (code.equals(correctCode)){
            // 验证成功从Redis删除验证码
            redisService.remove(JwtUtil.getEmailfromEmailCodeToken(emailCodeToken));
            return  true;
        }else {
            return false;
        }
    }
}
