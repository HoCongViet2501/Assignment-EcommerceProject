package com.assignment.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.assignment.springboot.data.entity")
@SpringBootApplication
public class AssignmentEcommerceProjectBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentEcommerceProjectBackEndApplication.class, args);
    }

}
