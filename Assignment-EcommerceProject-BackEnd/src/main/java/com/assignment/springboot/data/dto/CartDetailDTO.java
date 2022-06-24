package com.assignment.springboot.data.dto;

import com.assignment.springboot.data.entity.Product;
import com.assignment.springboot.data.entity.ShoppingSession;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
