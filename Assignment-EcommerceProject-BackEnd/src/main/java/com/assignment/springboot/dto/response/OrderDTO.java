package com.assignment.springboot.dto.response;

import com.assignment.springboot.entity.Customer;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class OrderDTO {
    private long id;
    private Customer customer;
    private String payment;
    private String status;
    private Date createdDate;
    private Date updatedDate;
}
