package com.example.message.service;

import com.example.message.model.EmailAddressManagement;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmailAddressManagementService {


    List<EmailAddressManagement> selectAllEmail();

    EmailAddressManagement getEmail();
    boolean  deleteEmailById(int id);

    boolean updateEmail(EmailAddressManagement emailAddressManagement);

    boolean addEmail(EmailAddressManagement emailAddressManagement);

}
