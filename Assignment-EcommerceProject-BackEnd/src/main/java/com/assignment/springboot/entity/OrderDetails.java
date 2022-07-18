package com.assignment.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_detail")
@Setter
@Getter
@NoArgsConstructor
public class OrderDetails {
    @EmbeddedId
    private OrderDetailsId orderDetailId;
    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "amount_product")
    private int amountProduct;
    private float price;
    @Column(name = "total_money")
    private float totalMoney;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;

    public OrderDetails(OrderDetailsId orderDetailId, int amountProduct, float price, float totalMoney, Date createdDate, Date updatedDate) {
        this.orderDetailId = orderDetailId;
        this.amountProduct = amountProduct;
        this.price = price;
        this.totalMoney = totalMoney;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
