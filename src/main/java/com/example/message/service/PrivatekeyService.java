package com.example.message.service;

import com.example.message.common.PageParam;
import com.example.message.model.Privatekey;


public interface PrivatekeyService {



	public Object getAllPrivatekey(PageParam<Privatekey> pageParam);

    public boolean removePrivatekeyById(int id);

    public Object addPrivatekey(Privatekey privatekey);

    public boolean updatePrivatekey(Privatekey privatekey);

    public Privatekey getPrivatekeyById(int id);




}