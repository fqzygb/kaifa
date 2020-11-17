package com.jmlt.kaifa.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object serialNumber = request.getSession().getAttribute("serialNumber");
        if (serialNumber!=null&&serialNumber!=""){
            return true;
        }

        response.sendRedirect(request.getContextPath()+"/addStockUser/hello");
   //     System.out.println("-------ngfjrtjtr---------------dfsgdsgg:");


        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }



}
