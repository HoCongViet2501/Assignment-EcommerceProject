package com.assignment.springboot.data.dto;
import lombok.*;
import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class CartDetailDTO {
    private ProductDTO product;
    private ShoppingSessionDTO session;
    private int amountProduct;
    private Date createdDate;
    private Date updatedDate;
}
