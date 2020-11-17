package com.jmlt.kaifa.mapper;

import com.jmlt.kaifa.entity.TMsg;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface TMsgMapper {
     TMsg findById(Integer id);
}
