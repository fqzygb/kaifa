package com.jmlt.kaifa.controller;

import com.jmlt.kaifa.entity.Msg;
import com.jmlt.kaifa.entity.StockUser;
import com.jmlt.kaifa.entity.UserInfo;
import com.jmlt.kaifa.entity.VisitLog;
import com.jmlt.kaifa.mapper.VisitLogMapper;
import com.jmlt.kaifa.service.StockUserService;
import com.jmlt.kaifa.service.UserInfoService;
import com.jmlt.kaifa.service.VisitLogService;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@RequestMapping("/sdgdrh")
@Controller
public class SearchController {
    private static Logger log = Logger.getLogger(SearchController.class.getClass());

    @Autowired
    private StockUserService stockUserService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private VisitLogService visitLogService;

    Msg msg = new Msg();

    @RequestMapping("/rhrehrt")
    public String findByNumber(String serialNumber, HttpServletRequest request, Model model){

        String msg = "";
        StockUser stockUser  = new StockUser();
        try {
            stockUser = stockUserService.findBySerialNumbers(serialNumber,request);
            if(stockUser != null){
                msg = "TargetUser";
            }else{
                msg = "NotTargetUser";
            }
            model.addAttribute("serialNumber",serialNumber);
            return msg;
        } catch (Exception e) {
            msg = "stockUserAdd";
            e.printStackTrace();
            model.addAttribute("serialNumber",serialNumber);
            return msg;
        }

    }


    @RequestMapping(value = "sdgerhger",method = RequestMethod.POST)
    @ResponseBody
    public Msg isOk( @Param("serialNumber") String serialNumber){//同意办理
        Msg msg = new Msg();
        try {
            msg = userInfoService.findBySerialNumbers(serialNumber);
            return msg;
        } catch (Exception e) {
            msg.setFlg("3");
            msg.setMsgContent("系统异常，请您重试！");
            e.printStackTrace();
            return msg;

        }
    }


    @RequestMapping("/gfhasnmk")
    @ResponseBody
    public Msg isNO(@Param("serialNumber") String serialNumber){//不同意办理
        Msg msg = new Msg();
        try {
            msg = userInfoService.findBySerialNumberNo(serialNumber);
            return msg;
        } catch (Exception e) {
            msg.setFlg("3");
            msg.setMsgContent("系统异常，请您重试！");
            e.printStackTrace();
            return msg;

        }

    }


    @RequestMapping("/beysdjkio")
    @ResponseBody
    public Msg delete(@Param("serialNumber") String serialNumber){//取消办理

        Msg msg = new Msg();

        try {
            msg = userInfoService.findBySerialNumberde(serialNumber);
            return msg;
        } catch (Exception e) {
            msg.setFlg("3");
            msg.setMsgContent("系统异常，请您重试！");
            e.printStackTrace();
            return msg;
        }


    }




}
