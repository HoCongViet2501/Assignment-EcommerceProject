package com.assignment.springboot.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "card_detail")
public class CartDetail{

    @EmbeddedId
    private CartDetailId cartDetailId;
    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @MapsId("sessionId")
    @ManyToOne
    @JoinColumn(name = "shopingsession_id")
    private ShoppingSession session;
    @Column(name = "amount_product")
    private int amountProduct;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;

    public ShoppingSession getSession() {
        return session;
    }

    public void setSession(ShoppingSession session) {
        this.session = session;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public CartDetail(CartDetailId cartDetailId, int amountProduct, Date createdDate, Date updatedDate) {
        this.cartDetailId = cartDetailId;
        this.amountProduct = amountProduct;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public CartDetail() {

    }

    public CartDetailId getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(CartDetailId cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public int getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}