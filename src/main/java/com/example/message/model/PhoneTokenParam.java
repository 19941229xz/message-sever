package com.example.message.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

/**
 * @program: message-sever
 * @description: 生成PhoneToken所需的参数实体类
 * @author: BitCoc
 * @create: 2019-08-01 11:13
 */

@Api(value = "PhoneTokenParam",description = "生成PhoneToken所需的参数实体类")
@Data
public class PhoneTokenParam {

    @ApiModelProperty(value = "需要发送的手机号",name = "phone")
    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$",message = "必须为正规手机号")
    private String phone;


    @ApiModelProperty(value = "模板ID",name = "phone")
    @NotNull(message = "模板ID不能为空")
    private int templateId;
}
