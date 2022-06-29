package com.assignment.springboot.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class CartDetailId implements Serializable {
    private int productId;
    private int sessionId;

    public CartDetailId(int productId, int sessionId) {
        this.productId = productId;
        this.sessionId = sessionId;
    }

    public CartDetailId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDetailId that = (CartDetailId) o;
        return productId == that.productId && sessionId == that.sessionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, sessionId);
    }
}
