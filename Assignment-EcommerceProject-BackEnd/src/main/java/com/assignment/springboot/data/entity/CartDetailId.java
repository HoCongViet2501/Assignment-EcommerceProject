package com.assignment.springboot.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@Embeddable
public class CartDetailId implements Serializable {
    private int productId;
    private int shoppingSessionId;

    public CartDetailId(int productId, int shoppingSessionId) {
        this.productId = productId;
        this.shoppingSessionId = shoppingSessionId;
    }

    public CartDetailId() {

    }
}
