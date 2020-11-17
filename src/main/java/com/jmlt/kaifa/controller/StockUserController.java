package com.jmlt.kaifa.controller;

import com.jmlt.kaifa.entity.StockUser;
import com.jmlt.kaifa.service.StockUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/addStockUser")
public class StockUserController {
    @Autowired
    private StockUserService stockUserservice;

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request){

       return "stockUserAdd";

    }

    @RequestMapping("/toAdd")
    public String add( String serialNumber){
        StockUser stockuser = new StockUser();
        stockuser.setSerialNumber(serialNumber);

        stockUserservice.insert(stockuser);
        return "stockUserAdd";
    }

    @ResponseBody
    @RequestMapping("/user")
    public String user(Integer id){
        StockUser user = stockUserservice.findById(id);
        return user.getSerialNumber();
    }



}
