package com.example.message.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "Template" ,description = "")
@Data  // 自动生成get set 和构造器
public class Template implements Serializable {
	// 
    @ApiModelProperty(value = "" ,name = "id")
	private Integer id;
	// 
    @ApiModelProperty(value = "" ,name = "code")
	private String code;
	// 
    @ApiModelProperty(value = "" ,name = "description")
	private String description;

}