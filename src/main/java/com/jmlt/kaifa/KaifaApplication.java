package com.jmlt.kaifa;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.jmlt.kaifa.mapper")//在启动类上面加入扫描的注解
public class KaifaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaifaApplication.class, args);
    }

}
