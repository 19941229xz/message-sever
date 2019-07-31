package com.example.message.dao;

import org.apache.ibatis.annotations.*;
import com.example.message.model.Privatekey;

import java.util.List;

public interface PrivatekeyDao {


    List<Privatekey> getAllPrivatekey(Privatekey privatekey);

    @Delete("delete from privatekey where id = #{id}")
    int removePrivatekeyById(int id);

    int addPrivatekey(Privatekey privatekey);

    int updatePrivatekey(Privatekey privatekey);

    @Select("select * from privatekey where id =#{id}")
    Privatekey getPrivatekeyById(int id);

    @Select("select * from privatekey where privatekeyName =#{privatekeyName}")
    Privatekey getPrivatekeyByPrivatekeyName(String privatekeyName);




}