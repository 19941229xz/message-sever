package com.example.message.service;

import com.example.message.common.HttpCode;
import com.example.message.common.MyException;
import com.example.message.common.MyRsp;
import com.example.message.dao.EmailAddressManagementDao;
import com.example.message.model.EmailAddressManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service("EmailAddressManagementService")
public class EmailAddressManagementServiceImpl implements  EmailAddressManagementService{

    @Autowired
    EmailAddressManagementDao emailAddressManagementDao;
    @Override
    public List<EmailAddressManagement> selectAllEmail() {
        List<EmailAddressManagement> emailAddressManagementList = emailAddressManagementDao.selectAllEmail();
        if (emailAddressManagementList.size()>0){
            return emailAddressManagementList;
        }else {
            throw  new MyException(HttpCode.ERROR).msg("查询所有邮箱失败");
        }
    }

    //从数据库随机获得一个存放的email跟授权码
    @Override
    public EmailAddressManagement getEmail() {
        List<EmailAddressManagement> emailAddressManagementList = emailAddressManagementDao.selectAllEmail();
        //生成一个小于等于列表size的索引i
        int i = new Random().nextInt(emailAddressManagementList.size());
        //取出索引为i的数据
        EmailAddressManagement emailAddressManagement = emailAddressManagementList.get(0);
        return  emailAddressManagement;
    }

    @Override
    public boolean deleteEmailById(int id) {
        return emailAddressManagementDao.deleteEmailById(id)==1?true:false;
    }

    @Override
    public boolean updateEmail(EmailAddressManagement emailAddressManagement) {
        return emailAddressManagementDao.updateEmail(emailAddressManagement)==1?true:false;
    }

    @Override
    public boolean addEmail(EmailAddressManagement emailAddressManagement) {
        return emailAddressManagementDao.addEmail(emailAddressManagement)==1?true:false;
    }
}
