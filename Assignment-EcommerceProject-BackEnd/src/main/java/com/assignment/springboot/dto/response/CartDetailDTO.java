package com.assignment.springboot.dto.response;

import com.assignment.springboot.dto.response.ProductDtoResponse;
import com.assignment.springboot.dto.response.ShoppingSessionDTO;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class CartDetailDTO {
    private ProductDtoResponse product;
    private ShoppingSessionDTO session;
    private int amountProduct;
    private Date createdDate;
    private Date updatedDate;
}
