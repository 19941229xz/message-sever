package com.example.message.common;

import org.apache.commons.lang.RandomStringUtils;


/**
 * @program: message-sever
 * @description: 验证码，邀请码，,随机加密字符串，4-6位数工具类
 * @author: BitCoc
 * @create: 2019-08-01 14:23
 */

public class commonUtil {
    /**
     *  
     *
     * @param type       类型  fiexed：混合  pureNum：纯数字  pureCharacter：纯字母
     * @param num        验证码长度
     * @param isAllUpper 是否全大写
     * @Description:  
     * @Param: [type, num, isAllUpper] 
     * @return: java.lang.Object
     */

    public static String code(String type, int num, boolean isAllUpper) {
        String res="";
        switch (type) {
            case "fiexed":
                res=RandomStringUtils.random(num, "abcdefghijklmnopqrstuvwxyz1234567890");
                break;
            case "pureNum":
                res=RandomStringUtils.random(num, "1234567890");
                break;
            case "pureCharacter":
                res=RandomStringUtils.random(num, "abcdefghijklmnopqrstuvwxyz");
                break;
            default:
                throw new MyException(HttpCode.ERROR).msg("随机串参数类型不匹配");
        }

        return isAllUpper?res.toUpperCase():res;
    }
}
