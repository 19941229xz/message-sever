package com.example.message.controller;

import com.example.message.common.MyRsp;
import com.example.message.common.PageParam;
import com.example.message.model.EmailAddressManagement;
import com.example.message.service.EmailAddressManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "系统email库管理接口",description = "这是一个系统发送邮件的email的接口")
@RestController
@Slf4j
@CrossOrigin
public class EmailAddressManagementController {

    @Autowired
    EmailAddressManagementService emailAddressManagementService;

    @ApiModelProperty("查询所有系统email库的方法")
    @PostMapping("/selectAllEmail")
    public Object selectAllEmail(){
        List<EmailAddressManagement> emailAddressManagementList = emailAddressManagementService.selectAllEmail();
        return emailAddressManagementList;
    }
    @ApiModelProperty("修改系统email库的方法")
    @PostMapping("/updateEmail")
    public Object updateEmail(@RequestBody EmailAddressManagement emailAddressManagement){
        return emailAddressManagementService.updateEmail(emailAddressManagement)? MyRsp.success(null).msg("更新成功"):MyRsp.error().msg("更新失败");
    }

    @ApiModelProperty("删除系统email库的方法")
    @PostMapping("/deleteEmailById/{id}")
    public Object deleteEmailById(@PathVariable("id") int id){
        return emailAddressManagementService.deleteEmailById(id)? MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @ApiModelProperty("添加系统email库的方法")
    @PostMapping("/addEmail")
    public Object addEmail(@RequestBody EmailAddressManagement emailAddressManagement){
        return emailAddressManagementService.addEmail(emailAddressManagement)? MyRsp.success(null).msg("添加成功"):MyRsp.error().msg("添加失败");
    }


}
