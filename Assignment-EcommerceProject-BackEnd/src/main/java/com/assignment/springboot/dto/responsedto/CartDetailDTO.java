package com.assignment.springboot.dto.responsedto;

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
