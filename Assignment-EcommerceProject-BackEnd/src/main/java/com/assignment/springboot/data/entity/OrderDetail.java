package com.assignment.springboot.data.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@EqualsAndHashCode
@Table(name = "order_detail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId orderDetailId;
    @Column(name = "amount_product")
    private int amountProduct;
    private float price;
    @Column(name = "total_money")
    private float totalMoney;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;

    public OrderDetail(OrderDetailId orderDetailId, int amountProduct, float price, float totalMoney, Date createdDate, Date updatedDate) {
        this.orderDetailId = orderDetailId;
        this.amountProduct = amountProduct;
        this.price = price;
        this.totalMoney = totalMoney;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public OrderDetail() {

    }

    public OrderDetailId getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(OrderDetailId orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
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
