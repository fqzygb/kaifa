package com.jmlt.kaifa.service.impl;

import com.jmlt.kaifa.entity.Msg;
import com.jmlt.kaifa.entity.UserInfo;
import com.jmlt.kaifa.mapper.UserInfoMapper;
import com.jmlt.kaifa.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public int insert(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    @Override
    public UserInfo findBySerialNumber(String serialNumber) {
        return userInfoMapper.findBySerialNumber(serialNumber);
    }

    @Override
    public int update(UserInfo userInfo) {
        return userInfoMapper.update(userInfo);
    }

    @Override
    public Msg findBySerialNumbers(String serialNumber) {//同意办理
        Msg msg = new Msg();
        UserInfo info = new UserInfo();
        String timeStr= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        if(!StringUtils.isEmpty(serialNumber)){
            UserInfo userInfo = this.findBySerialNumber(serialNumber);
            if(userInfo != null){
                if(userInfo.getIsConsent() == null || userInfo.getIsConsent().equals("0")){//要避免空指针异常，null.equals()会造成空指针异常
                    userInfo.setSerialNumber(serialNumber);
                    userInfo.setConfirmTime(timeStr);
                    userInfo.setIsConsent("1");
                    this.update(userInfo);
                    msg.setFlg("1");
                    msg.setMsgContent("您已成功登记，于次月生效，请您留意！");
                }else if(userInfo.getIsConsent().equals("1")){//要避免空指针异常
                    msg.setFlg("4");
                    msg.setMsgContent("您已经参加过活动，不用重新参加！");
                }
            }else{
                msg.setFlg("3");
                msg.setMsgContent("系统异常，请您重试！");
            }
        }else {
            msg.setFlg("3");
            msg.setMsgContent("系统异常，请您重试！");
        }

        return msg;
    }

    @Override
    public Msg findBySerialNumberNo(String serialNumber) {//不同意办理
        Msg msg = new Msg();
        UserInfo info = new UserInfo();
        String timeStr= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        if(!StringUtils.isEmpty(serialNumber)){
            UserInfo userInfo = this.findBySerialNumber(serialNumber);
            if(userInfo != null){
                if( userInfo.getIsConsent().equals("1")){//要避免空指针异常，null.equals()会造成空指针异常
                    msg.setFlg("4");
                    msg.setMsgContent("您已经成功登记活动，请按取消按钮取消活动！");
                }else {
                    info.setSerialNumber(serialNumber);
                    info.setConfirmTime(timeStr);//不同意的时间
                    info.setIsConsent("0");
                    this.update(info);
                    msg.setFlg("1");
                    msg.setMsgContent("业务登记失败，期待您的参与！");
                }
            }else{
                msg.setFlg("3");
                msg.setMsgContent("系统异常，请您重试！");
            }
        }else {
            msg.setFlg("3");
            msg.setMsgContent("系统异常，请您重试！");
        }

        return msg;
    }

    @Override
    public Msg findBySerialNumberde(String serialNumber) {
        Msg msg = new Msg();

        //设置时间格式
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            //获得实体类
            Calendar ca = Calendar.getInstance();
           //设置最后一天
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
           //最后一天格式化
            String lastDay = format.format(ca.getTime());

            String timeStr= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String timeStr1= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            UserInfo userInfo = new UserInfo();
        if (!StringUtils.isEmpty(serialNumber)) {
            UserInfo info = this.findBySerialNumber(serialNumber);
            if (info!=null){//查询数据库要判空
                userInfo.setSerialNumber(serialNumber);
                userInfo.setConfirmTime(timeStr);//取消时间
                userInfo.setIsConsent("0");
                if (userInfo.getIsConsent() == null ||  info.getIsConsent().equals("0")) {
                    msg.setFlg("4");
                    msg.setMsgContent("您未参加活动，不用取消！");

                }else {
                    if (Integer.parseInt(timeStr1)<Integer.parseInt(lastDay)) {
                       this.update(userInfo);
                            msg.setFlg("1");
                            msg.setMsgContent("取消成功，期待您的参与！");

                    }else{
                        msg.setFlg("5");
                        msg.setMsgContent("取消时间已过，不允许取消，详情请咨询10010客服！");

                    }
                }
            } else{
            msg.setFlg("3");
            msg.setMsgContent("系统异常，请您重试！");
        }
    }else{
            msg.setFlg("3");
            msg.setMsgContent("系统异常，请您重试！");
        }

        return msg;

    }
}
