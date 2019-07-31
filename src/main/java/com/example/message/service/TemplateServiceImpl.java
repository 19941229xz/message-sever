package com.example.message.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.example.message.common.HttpCode;
import com.example.message.common.MyException;
import com.example.message.common.PageParam;
import com.example.message.dao.TemplateDao;
import com.example.message.model.Template;

import java.util.List;

@Slf4j
@Service
@Transactional
public class TemplateServiceImpl implements TemplateService {


    @Autowired
    TemplateDao templateDao;

    @Override
    @Transactional(readOnly = true)
    public Object getAllTemplate(PageParam<Template> pageParam){

        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Template> templateList=templateDao.getAllTemplate(pageParam.getModel());
        PageInfo<Template> templatePageInfo = new PageInfo<Template>(templateList);

        return templatePageInfo;

    }

    @CacheEvict(value = "templates",key = "#p0")
    @Override
    public boolean removeTemplateById(int id){
        return templateDao.removeTemplateById(id)==1;
    }

    @CachePut(value = "templates",key = "#p0.id")
    @Override
    public Object addTemplate(Template template){
        templateDao.addTemplate(template);

        return templateDao.getTemplateById(template.getId());
    }

    @CacheEvict(value = "templates",key = "#p0.id")
    @Override
    public boolean updateTemplate(Template template){
        if(StringUtils.isEmpty(template.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改template时，id不能为空");
        }

        return templateDao.updateTemplate(template)==1;
    }

    @Cacheable(key = "#p0",value="templates")
    @Override
    @Transactional(readOnly = true)
    public Template getTemplateById(int id){
        return templateDao.getTemplateById(id);

    }




}
