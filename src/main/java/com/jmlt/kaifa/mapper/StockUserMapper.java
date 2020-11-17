package com.jmlt.kaifa.mapper;


import com.jmlt.kaifa.entity.StockUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockUserMapper {
    int  insert(StockUser stockuser) ;
    StockUser findById(Integer id);
    StockUser findBySerialNumber(String serialNumber);

}
