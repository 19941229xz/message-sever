package com.example.message.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class EmailCodeTokenParam {

    @ApiModelProperty(value = "用户email地址" ,name = "email")
    @Email(message = "邮箱格式不正确")
    //@NotEmpty(message = "邮箱不能为空")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message = "邮箱格式不正确")
    private String emailAdress;

    @ApiModelProperty(value = "验证码" ,name="code")
    private int code;
}
