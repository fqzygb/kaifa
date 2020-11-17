package com.jmlt.kaifa.service.impl;

import com.jmlt.kaifa.entity.TMsg;
import com.jmlt.kaifa.mapper.TMsgMapper;
import com.jmlt.kaifa.service.TMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TMsgServiceImpl implements TMsgService {

    @Autowired
    private TMsgMapper tMsgMapper;
    @Override
    public TMsg findById(Integer id) {
        return tMsgMapper.findById(id);
    }
}
