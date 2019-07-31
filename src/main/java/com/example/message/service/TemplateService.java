package com.example.message.service;

import com.example.message.common.PageParam;
import com.example.message.model.Template;


public interface TemplateService {



	public Object getAllTemplate(PageParam<Template> pageParam);

    public boolean removeTemplateById(int id);

    public Object addTemplate(Template template);

    public boolean updateTemplate(Template template);

    public Template getTemplateById(int id);




}