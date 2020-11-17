package com.jmlt.kaifa.service.impl;

import com.jmlt.kaifa.entity.StockUser;
import com.jmlt.kaifa.entity.UserInfo;
import com.jmlt.kaifa.entity.VisitLog;
import com.jmlt.kaifa.mapper.StockUserMapper;
import com.jmlt.kaifa.service.StockUserService;
import com.jmlt.kaifa.service.UserInfoService;
import com.jmlt.kaifa.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class StockUserServiceImpl implements StockUserService {

    @Autowired
    private StockUserMapper stockUsermapper;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private VisitLogService visitLogService;
    @Override
    public     int  insert(StockUser stockuser) {

        return   stockUsermapper.insert(stockuser);

    }

    @Override
    public StockUser findById(Integer id) {
        return stockUsermapper.findById(id);
    }

    @Override
    public StockUser findBySerialNumber(String serialNumber) {
        return stockUsermapper.findBySerialNumber(serialNumber);
    }

    @Override
    public StockUser findBySerialNumbers(String serialNumber, HttpServletRequest request) {
        String timeStr= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String remoteHost = getRemoteHost(request);
        StockUser number = null;
        if(!StringUtils.isEmpty(serialNumber)){
            //插入日志记录
            VisitLog visitLog = new VisitLog();
            visitLog.setClientIp(remoteHost);
            visitLog.setSerialNumber(serialNumber);
            visitLog.setVisitTime(timeStr);
            visitLogService.insert(visitLog);

            //
            UserInfo userInfo = new UserInfo();
            StockUser stockUser = new StockUser();
            stockUser.setSerialNumber(serialNumber);

            //判断是否是目标用户
            number = this.findBySerialNumber(serialNumber);
            if(number != null){
                userInfo.setIsTarget("1");
                userInfo.setSerialNumber(number.getSerialNumber());
                this.insertEntity(serialNumber,userInfo,true);
            }else{
                this.insertEntity(serialNumber,userInfo,false);
            }
        }
        return number;
    }

    public void insertEntity(String serialNumber,UserInfo userInfo,boolean isTarget){
        if(!StringUtils.isEmpty(serialNumber)){
            UserInfo info = userInfoService.findBySerialNumber(serialNumber);
            //不管是不是目标用户都要插入数据表
            if (info!=null ){//user_info 表已经存在，不再插入（可能存在非目标用户变成目标用户的情况）
                if(isTarget){
                    userInfo.setIsTarget("1");
                    userInfo.setSerialNumber(serialNumber);
                    userInfoService.update(userInfo);
                }
            }else {
                if(isTarget){//是目标用户
                    userInfo.setIsTarget("1");
                    userInfo.setSerialNumber(serialNumber);
                }else{//不是目标用户
                    userInfo.setIsTarget("0");
                    userInfo.setSerialNumber(serialNumber);
                }
                userInfoService.insert(userInfo);
            }
        }
    }

    /**
     * 获取客户端的ip地址
     * @param request
     * @return
     */
    public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
}
