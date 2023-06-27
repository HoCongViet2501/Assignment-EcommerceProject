package com.assignment.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "card_detail")
public class CartDetail {

    @EmbeddedId
    private CartDetailId cartDetailId;
    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @MapsId("sessionId")
    @ManyToOne
    @JoinColumn(name = "shoping_session_id")
    private ShoppingSession session;
    @Column(name = "amount_product")
    private int amountProduct;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;


    public CartDetail(CartDetailId cartDetailId, int amountProduct, Date createdDate, Date updatedDate) {
        this.cartDetailId = cartDetailId;
        this.amountProduct = amountProduct;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public CartDetail() {

    }
}