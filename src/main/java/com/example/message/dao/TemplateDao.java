package com.example.message.dao;

import org.apache.ibatis.annotations.*;
import com.example.message.model.Template;

import java.util.List;

public interface TemplateDao {


    List<Template> getAllTemplate(Template template);

    @Delete("delete from template where id = #{id}")
    int removeTemplateById(int id);

    int addTemplate(Template template);

    int updateTemplate(Template template);

    @Select("select * from template where id =#{id}")
    Template getTemplateById(int id);

    @Select("select * from template where templateName =#{templateName}")
    Template getTemplateByTemplateName(String templateName);




}