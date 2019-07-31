package com.example.message.common;

import com.wenli.springbootdemo.model.User;
import com.wenli.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("email")
@CrossOrigin
public class EmailController {

    @Autowired
    UserService userService;

    @GetMapping("/sendPasswordToEmail/{email}")
    public Object user(@PathVariable("email") String email) throws Exception {

        User u = userService.getUserByEmail(email);
        if (u != null){
            if (u.getIsActive() == 1){
                String pw = (new Random().nextInt(899999) + 100000) + "mm";
                u.setPassword(pw);
                userService.updateUser(u);

                EmailUtil.sendEmail(pw, email);
                return MyResponse.success(null).msg("邮件发送成功，请注意查收");
            }else {
                return MyResponse.error().msg("该账号未激活");
            }
        }
        return MyResponse.error().msg("邮箱未注册");
    }

}
