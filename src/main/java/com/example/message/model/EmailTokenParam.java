package com.example.message.model;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class EmailTokenParam {

    @ApiModelProperty(value = "email标题" ,name = "userName")
    @NotEmpty(message = "标题不能为空")
    @Size(min = 1,max = 25 ,message = "标题长度最大只能是25个汉字")
    private String  title;


    @ApiModelProperty(value = "用户email地址" ,name = "email")
    // @Email(message = "邮箱格式不正确")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message = "邮箱格式不正确")
    private String emailAdress;

    @ApiModelProperty(value = "email内容" ,name = "email")
    private String text;
}
