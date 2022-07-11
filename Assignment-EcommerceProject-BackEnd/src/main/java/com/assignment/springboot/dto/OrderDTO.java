package com.assignment.springboot.dto;

import com.assignment.springboot.entity.Customer;
import com.assignment.springboot.entity.Employee;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class OrderDTO {
    private Integer id;
    private Employee employee;
    private Customer customer;
    private String payment;
    private String status;
    private Date createdDate;
    private Date updatedDate;
}
