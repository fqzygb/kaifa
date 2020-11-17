package com.jmlt.kaifa.mapper;

import com.jmlt.kaifa.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
   int  insert(UserInfo  userInfo);
   UserInfo findBySerialNumber(String serialNumber);
   int update(UserInfo  userInfo);

}
