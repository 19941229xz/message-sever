package com.example.message.dao;

import com.example.message.model.EmailAddressManagement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmailAddressManagementDao {

    @Select("select id,emailAddress,authorizationCode from emailaddressmanagement")
    List<EmailAddressManagement> selectAllEmail();

    EmailAddressManagement getEmail();

    @Delete("delete from emailaddressmanagement where id = #{id}")
    int deleteEmailById(int id);

    int updateEmail(EmailAddressManagement emailAddressManagement);

    int addEmail(EmailAddressManagement emailAddressManagement);
}
