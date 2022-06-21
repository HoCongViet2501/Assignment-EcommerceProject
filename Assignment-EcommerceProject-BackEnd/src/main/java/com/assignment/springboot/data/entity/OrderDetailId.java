package com.assignment.springboot.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class OrderDetailId implements Serializable {
    private static final long serialVersionUID = 4751710654285987677L;
    private int orderId;
    private int productId;

    public OrderDetailId(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderDetailId() {

    }
}
