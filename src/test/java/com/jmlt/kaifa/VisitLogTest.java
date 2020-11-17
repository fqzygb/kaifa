package com.jmlt.kaifa;

import com.jmlt.kaifa.entity.VisitLog;
import com.jmlt.kaifa.service.VisitLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VisitLogTest  {
    @Autowired
    private VisitLogService visitLogService;

    @Test
    public void insert(){
        VisitLog visitLog = new VisitLog();
        visitLog.setClientIp("ryeyeyey");
        visitLogService.insert(visitLog);
    }

}
