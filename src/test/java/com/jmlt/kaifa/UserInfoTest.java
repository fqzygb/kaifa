package com.jmlt.kaifa;

import com.jmlt.kaifa.entity.UserInfo;
import com.jmlt.kaifa.mapper.UserInfoMapper;
import com.jmlt.kaifa.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)

public class UserInfoTest {
    @Autowired
    private UserInfoService userInfoService;
    @Test
   public void say(){
        System.out.println("你好，世界");
    }

    @Test
    public void insert(){
        UserInfo userInfo = new UserInfo();
      //  userInfo.setId(1);
        userInfo.setIsConsent("cfhfghfg");
     //   userInfo.setConfirmTime("rytryrtyt");
        userInfo.setIsTarget("1");
        userInfo.setSerialNumber("46576786868");
        userInfoService.insert(userInfo);
    }

}

