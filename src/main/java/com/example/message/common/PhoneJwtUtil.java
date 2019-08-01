package com.example.message.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class PhoneJwtUtil {


    private static final long expire_time = 5 * 60 * 1000; //五分钟内有效

    //通过模板id  随机验证码 电话号码 和后台私钥 生成emailToken
    public static String createPhoneToken(int templateId, String privateKey,String phone) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + expire_time);
        Algorithm algorithm = Algorithm.HMAC256(privateKey);
        return JWT.create().withClaim("templateId", templateId)
                .withClaim("phone", phone)
                .withExpiresAt(date).sign(algorithm);
    }

    //解码emailToken 获取模板id
    public static int  getTemplateIdfromPhoneToken(String phoneToken){
        try {
            DecodedJWT jwt = JWT.decode(phoneToken);
            return jwt.getClaim("templateId").asInt();
        } catch (Exception e) {
            // Todo  需完善
            return 0;
        }
    }
//    //解码emailToken 获取随机验证码
//    public static String getCodefromPhoneToken(String phoneToken){
//        try {
//            DecodedJWT jwt = JWT.decode(phoneToken);
//            return jwt.getClaim("code").asString();
//        } catch (Exception e) {
//            return null;
//        }
//    }


    //解码emailToken 获取电话号码
    public static String getPhonefromPhoneToken(String phoneToken){
        try {
            DecodedJWT jwt = JWT.decode(phoneToken);
            return jwt.getClaim("phone").asString();
        } catch (Exception e) {
            return null;
        }
    }

    // 校验方法
    public static boolean verify(String token, String username, String secret) {

        try {
            //校验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            //吧校验器加载到验证器
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username)
                    .build();


            DecodedJWT verify = verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.debug("ex happen");
            return false;
        }


    }

    //签名方法
    public static String sign(String username, String secret) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + expire_time);

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create().withClaim("username", username)
                .withExpiresAt(date).sign(algorithm);


    }


    public static String getUsername(String token) {

        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (Exception e) {
            return null;
        }


    }


}
