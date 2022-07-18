package com.assignment.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.assignment.springboot.repository")
@EntityScan(basePackages = "com.assignment.springboot.entity")
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
