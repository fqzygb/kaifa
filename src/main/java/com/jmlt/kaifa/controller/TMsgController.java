package com.jmlt.kaifa.controller;

import com.jmlt.kaifa.entity.TMsg;
import com.jmlt.kaifa.service.TMsgService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/msg")
public class TMsgController {
    @Autowired
    private TMsgService tMsgService;

   @GetMapping("/getMsg")
    public String getMsg(@Param("id") Integer id) {

        TMsg tMsg = tMsgService.findById(id);
        return tMsg.getMessage();
    }
}
