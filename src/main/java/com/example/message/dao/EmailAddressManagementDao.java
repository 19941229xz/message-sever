package com.example.message.dao;

import com.example.message.model.EmailAddressManagement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmailAddressManagementDao {

    @Select("select id,emailAddress,authorizationCode from emailaddressmanagement")
    public List<EmailAddressManagement> selectAllEmail();

    public EmailAddressManagement getEmail();

    @Delete("delete from emailaddressmanagement where id = #{id}")
    public int deleteEmailById(int id);

    public int updateEmail(EmailAddressManagement emailAddressManagement);

    public int addEmail(EmailAddressManagement emailAddressManagement);
}
