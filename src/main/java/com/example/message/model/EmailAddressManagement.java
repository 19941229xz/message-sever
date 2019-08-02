package com.example.message.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class EmailAddressManagement {
    @ApiModelProperty(value = "主键" ,name = "id")
    private  int id;

    @ApiModelProperty(value = "系统发邮件的email地址" ,name = "email")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message = "邮箱格式不正确")
    private  String emailAddress;

    @ApiModelProperty(value = "邮件的email的授权码" ,name = "authorizationCode")
    @NotEmpty
    private String authorizationCode;

}
