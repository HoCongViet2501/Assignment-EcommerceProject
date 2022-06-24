package com.assignment.springboot.data.dto;

import com.assignment.springboot.data.entity.Customer;
import com.assignment.springboot.data.entity.Employee;
import com.assignment.springboot.data.entity.OrderDetail;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private List<OrderDetail> orderDetails;
}
