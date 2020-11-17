package com.jmlt.kaifa.service.impl;

import com.jmlt.kaifa.entity.VisitLog;
import com.jmlt.kaifa.mapper.VisitLogMapper;
import com.jmlt.kaifa.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitLogServiceImpl implements VisitLogService {
    @Autowired
    private VisitLogMapper visitLogMapper;
    @Override
    public int insert(VisitLog visitLog) {
        return visitLogMapper.insert(visitLog);
    }
}
