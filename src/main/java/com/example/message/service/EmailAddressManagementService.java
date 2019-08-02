package com.example.message.service;

import com.example.message.model.EmailAddressManagement;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmailAddressManagementService {


    public List<EmailAddressManagement> selectAllEmail();

    public EmailAddressManagement getEmail();
    public boolean  deleteEmailById(int id);

    public boolean updateEmail(EmailAddressManagement emailAddressManagement);

    public boolean addEmail(EmailAddressManagement emailAddressManagement);

}
