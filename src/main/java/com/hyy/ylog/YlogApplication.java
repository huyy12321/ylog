package com.hyy.ylog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hyy
 */
@SpringBootApplication
@MapperScan("com.hyy.ylog.mapper")
public class YlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(YlogApplication.class, args);
    }

}
