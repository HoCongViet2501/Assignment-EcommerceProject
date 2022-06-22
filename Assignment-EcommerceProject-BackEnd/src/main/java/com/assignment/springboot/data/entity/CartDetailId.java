package com.assignment.springboot.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
@Setter
@Getter
@EqualsAndHashCode
@Embeddable
public  class CartDetailId implements Serializable {
    private int productId;
    private int sessionId;

    public CartDetailId(int productId, int sessionId) {
        this.productId = productId;
        this.sessionId = sessionId;
    }

    public CartDetailId() {

    }
}
