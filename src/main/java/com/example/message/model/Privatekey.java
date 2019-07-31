package com.example.message.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Privatekey" ,description = "附加的秘钥字符串")
@Data  // 自动生成get set 和构造器
public class Privatekey implements Serializable {
	// 主键ID
    @ApiModelProperty(value = "主键ID" ,name = "id")
	private Integer id;
	// 附加的加密字符串
    @ApiModelProperty(value = "附加的加密字符串" ,name = "privatekey")
	private String privatekey;

}