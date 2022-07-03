package com.assignment.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EntityScan(basePackages = "com.assignment.springboot.data.entity")
@SpringBootApplication
public class AssignmentEcommerceProjectBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentEcommerceProjectBackEndApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
