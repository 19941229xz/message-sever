package com.example.message.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.message.common.*;
import com.example.message.model.Template;
import com.example.message.service.TemplateService;

import javax.validation.Valid;

@Api(value = "template模块接口",description = "这是一个短信发送模板模块的接口文档")
@RestController
@Slf4j
@CrossOrigin
public class TemplateController {

	@Autowired
    TemplateService templateService;

	@ApiOperation("查询所有短信发送模板 支持多条件分页排序查询")
    @PostMapping("/getAllTemplate")
    public Object getAllTemplate(@RequestBody PageParam<Template> pageParam){
        return MyRsp.success(templateService.getAllTemplate(pageParam)).msg("查询成功");
    }

    @GetMapping("/removeTemplateById/{id}")
    public Object removeTemplateByTemplateName(@PathVariable("id") int id){

        return templateService.removeTemplateById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @PostMapping("/addTemplate")
    public Object addTemplate(@RequestBody @Valid Template templateParam){
        Template template=(Template)templateService.addTemplate(templateParam);

        return template!=null?MyRsp.success(template).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }


    @PutMapping("/updateTemplate")
    public Object updateTemplate(@RequestBody@Valid Template template){
        return templateService.updateTemplate(template)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @GetMapping("/getTemplateById/{id}")
    public Object getTemplateById(@PathVariable("id") int id){

        Template template=templateService.getTemplateById(id);
        return template!=null?MyRsp.success(template):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
	
}