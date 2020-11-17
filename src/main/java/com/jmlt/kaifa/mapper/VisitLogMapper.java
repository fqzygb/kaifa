package com.jmlt.kaifa.mapper;

import com.jmlt.kaifa.entity.VisitLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisitLogMapper {
    int insert(VisitLog visitLog);
}
