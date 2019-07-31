package com.example.message.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Template" ,description = "短信发送模板")
@Data  // 自动生成get set 和构造器
public class Template implements Serializable {
	// 主键ID
    @ApiModelProperty(value = "主键ID" ,name = "id")
	private Integer id;
	// 模板的Code
    @ApiModelProperty(value = "模板的Code" ,name = "code")
	private String code;
	// 模板的描述
    @ApiModelProperty(value = "模板的描述" ,name = "description")
	private String description;

}