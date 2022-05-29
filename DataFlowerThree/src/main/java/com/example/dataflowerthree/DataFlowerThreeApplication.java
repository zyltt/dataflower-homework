package com.example.dataflowerthree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.dataflowerthree.mapper")
public class DataFlowerThreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataFlowerThreeApplication.class, args);
    }

}
