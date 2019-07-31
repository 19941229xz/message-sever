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
import com.example.message.dao.PrivatekeyDao;
import com.example.message.model.Privatekey;

import java.util.List;

@Slf4j
@Service
@Transactional
public class PrivatekeyServiceImpl implements PrivatekeyService {


	@Autowired
    PrivatekeyDao privatekeyDao;

	@Override
    @Transactional(readOnly = true)
	public Object getAllPrivatekey(PageParam<Privatekey> pageParam){
    
    	PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }


        List<Privatekey> privatekeyList=privatekeyDao.getAllPrivatekey(pageParam.getModel());
        PageInfo<Privatekey> privatekeyPageInfo = new PageInfo<Privatekey>(privatekeyList);

        return privatekeyPageInfo;
    
    }

	@CacheEvict(value = "privatekeys",key = "#p0")
    @Override
    public boolean removePrivatekeyById(int id){
    	return privatekeyDao.removePrivatekeyById(id)==1;
    }

	@CachePut(value = "privatekeys",key = "#p0.id")
    @Override
    public Object addPrivatekey(Privatekey privatekey){
        privatekeyDao.addPrivatekey(privatekey);

        return privatekeyDao.getPrivatekeyById(privatekey.getId());
    }

	@Override
    public boolean updatePrivatekey(Privatekey privatekey){
    	if(StringUtils.isEmpty(privatekey.getId())){
            throw new MyException(HttpCode.ERROR).msg("通过id修改privatekey时，id不能为空");
        }

        return privatekeyDao.updatePrivatekey(privatekey)==1;
    }

	@Cacheable(key = "#p0",value="privatekeys")
    @Override
    @Transactional(readOnly = true)
    public Privatekey getPrivatekeyById(int id){
    	return privatekeyDao.getPrivatekeyById(id);
    	
    }




}
