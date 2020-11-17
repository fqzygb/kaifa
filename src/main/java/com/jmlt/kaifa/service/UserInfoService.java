package com.jmlt.kaifa.service;

import com.jmlt.kaifa.entity.Msg;
import com.jmlt.kaifa.entity.UserInfo;

public interface UserInfoService {
    public  int insert(UserInfo userInfo);

    public   UserInfo findBySerialNumber(String serialNumber);

    public   int update(UserInfo  userInfo);

    Msg findBySerialNumbers(String serialNumber);

    Msg findBySerialNumberNo(String serialNumber);

    Msg findBySerialNumberde(String serialNumber);



}
