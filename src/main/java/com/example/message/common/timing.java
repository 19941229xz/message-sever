package com.example.message.common;

import com.example.message.model.Privatekey;
import com.example.message.service.PrivatekeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static javafx.scene.input.KeyCode.P;

/**
 * @program: message-sever
 * @description: 用于定时修改privateKey秘钥
 * @author: BitCoc
 * @create: 2019-08-02 08:48
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class timing {


    @Autowired
    PrivatekeyService privatekeyService;

    @Scheduled(cron = "* * 1 * * ?")
    public void timing(){

        String key = commonUtil.code("fiexed",4,false);

       Privatekey privatekey = new Privatekey();

       privatekey.setId(1);
       privatekey.setPrivatekey(key);

        privatekeyService.updatePrivatekey(privatekey);
    }
}
