package com.assignment.springboot.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailId that = (OrderDetailId) o;
        return orderId == that.orderId && productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }
}
