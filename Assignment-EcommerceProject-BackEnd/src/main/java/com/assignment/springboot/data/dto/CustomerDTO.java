package com.assignment.springboot.data.dto;

import com.assignment.springboot.data.entity.Order;
import lombok.*;

import javax.persistence.Column;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class CustomerDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String gmail;
    private String phoneNumber;
    private String address;
    private List<Order> orders;
}
