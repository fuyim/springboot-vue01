package com.obtk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.obtk.mapper")
public class SpringbootvueApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootvueApplication.class, args);
    }

}
