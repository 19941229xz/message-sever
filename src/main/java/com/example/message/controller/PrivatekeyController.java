package com.example.message.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.message.common.*;
import com.example.message.model.Privatekey;
import com.example.message.service.PrivatekeyService;

import javax.validation.Valid;

@Api(value = "privatekey模块接口",description = "这是一个附加的秘钥字符串模块的接口文档")
@RestController
@Slf4j
@CrossOrigin
public class PrivatekeyController {

	@Autowired
    PrivatekeyService privatekeyService;

	@ApiOperation("查询所有附加的秘钥字符串 支持多条件分页排序查询")
    @PostMapping("/getAllPrivatekey")
    public Object getAllPrivatekey(@RequestBody PageParam<Privatekey> pageParam){
        return MyRsp.success(privatekeyService.getAllPrivatekey(pageParam)).msg("查询成功");
    }

    @GetMapping("/removePrivatekeyById/{id}")
    public Object removePrivatekeyByPrivatekeyName(@PathVariable("id") int id){

        return privatekeyService.removePrivatekeyById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addPrivatekey")
    public Object addPrivatekey(@RequestBody @Valid Privatekey privatekeyParam){
        Privatekey privatekey=(Privatekey)privatekeyService.addPrivatekey(privatekeyParam);

        return privatekey!=null?MyRsp.success(privatekey).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updatePrivatekey")
    public Object updatePrivatekey(@RequestBody@Valid Privatekey privatekey){
        return privatekeyService.updatePrivatekey(privatekey)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getPrivatekeyById/{id}")
    public Object getPrivatekeyById(@PathVariable("id") int id){

        Privatekey privatekey=privatekeyService.getPrivatekeyById(id);
        return privatekey!=null?MyRsp.success(privatekey):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}