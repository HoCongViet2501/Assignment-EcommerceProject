package com.assignment.springboot.data.dto;

import com.assignment.springboot.data.entity.CartDetail;
import com.assignment.springboot.data.entity.Customer;
import lombok.*;
import java.util.Date;
import java.util.List;
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
    private List<CartDetail> cartDetails;
}
