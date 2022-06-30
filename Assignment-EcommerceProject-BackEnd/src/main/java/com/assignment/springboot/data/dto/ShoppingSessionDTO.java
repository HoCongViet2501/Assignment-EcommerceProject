package com.assignment.springboot.data.dto;

import com.assignment.springboot.data.entity.Customer;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class ShoppingSessionDTO {
    private int id;
    private Customer customer;
    private Date createdDate;
    private Date updatedDate;
}
