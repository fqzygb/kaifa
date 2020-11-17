package com.jmlt.kaifa.service;

import com.jmlt.kaifa.entity.StockUser;

import javax.servlet.http.HttpServletRequest;

public interface StockUserService {
   public int  insert(StockUser stockuser) ;

   public StockUser findById(Integer id);

   public StockUser findBySerialNumber(String serialNumber);

   StockUser findBySerialNumbers(String serialNumber, HttpServletRequest request);
}
