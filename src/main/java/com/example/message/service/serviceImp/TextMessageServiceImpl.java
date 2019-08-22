package com.example.message.service.serviceImp;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.example.message.common.PhoneJwtUtil;
import com.example.message.common.commonUtil;
import com.example.message.model.Template;
import com.example.message.service.PrivatekeyService;
import com.example.message.service.RedisService;
import com.example.message.service.TemplateService;
import com.example.message.service.TextMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @program: message-service
 * @description:
 * @author: BitCoc
 * @create: 2019-07-31 18:23
 **/
@Slf4j
@Service
public class TextMessageServiceImpl implements TextMessageService {

    //注入模板数据库信息
    @Autowired
    TemplateService templateService;

    @Autowired
    PrivatekeyService privatekeyService;

    @Autowired
    RedisService redisService;


    /** 
    * @Description:  反解Token的方法
    * @Param: [phoneToken] 
    * @return: void 
    */
    @Override
    @Async
    public void sendTextMessage(String phoneToken) {

        // 通过token获取模板ID
        int templateId= PhoneJwtUtil.getTemplateIdfromPhoneToken(phoneToken);
        // 通过获取的模板ID从数据库中获取到模板信息
        Template template = templateService.getTemplateById(templateId);

        // 随机生成验证码
        String code = commonUtil.code("pureNum",4,false);

        // 通过token获取电话号码
        String phone = PhoneJwtUtil.getPhonefromPhoneToken(phoneToken);

        //调用阿里云短信服务API进行接口对接使用
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai","LTAIcnsA5tZmp7mk", "cqmWkfjGK34X8xGtJyWKz3u4ptl19A");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-shanghai");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "BitCoc");
        request.putQueryParameter("TemplateCode", template.getCode());
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info(response.getData());

            if (response.getData().contains("OK")){
                redisService.set(phone,code,5*60*1000L);
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param templateId 阿里云短信模板id
     * @param phone      目标手机号
     * @return
     * @apiNote 同过阿里云短信模板id，目标手机号获得加密Token
     */
    @Override
    public String getPhoneToken(int templateId,String phone) {

        String parviatekey = privatekeyService.getPrivatekeyById(1).getPrivatekey();

        return PhoneJwtUtil.createPhoneToken(templateId,parviatekey,phone);
    }


    /** 
    * @Description: 通过加密Token和验证码进行验证 
    * @Param: [phoneToken, code] 
    * @return: boolean 
    */
    @Override
    public boolean verifyCodeByPhone(String phoneToken, String code) {

        // 通过token获取电话号码
        String phone = PhoneJwtUtil.getPhonefromPhoneToken(phoneToken);


        if(redisService.get(phone) == null){
            return false;
        }

        if(redisService.get(phone).toString().equals(code)){
            redisService.remove(phone);
            return true;
        }
            return false;
    }

}
