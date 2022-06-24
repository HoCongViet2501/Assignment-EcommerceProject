package com.assignment.springboot.data.dto;

import com.assignment.springboot.data.entity.Customer;
import com.assignment.springboot.data.entity.Product;
import lombok.*;
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class RatingDTO {
    private int id;
    private int voteStar;
    private CustomerDTO customer;
    private ProductDTO product;
}
