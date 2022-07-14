package com.assignment.springboot.dto.response;

import com.assignment.springboot.entity.Customer;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class ShoppingSessionDTO {
    private long id;
    private Customer customer;
    private Date createdDate;
    private Date updatedDate;
}
