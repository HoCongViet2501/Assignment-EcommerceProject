package com.assignment.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class CartDetailIds implements Serializable {
    private long productId;
    private long sessionId;

    public CartDetailIds(int productId, int sessionId) {
        this.productId = productId;
        this.sessionId = sessionId;
    }

    public CartDetailIds() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDetailIds that = (CartDetailIds) o;
        return productId == that.productId && sessionId == that.sessionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, sessionId);
    }
}
