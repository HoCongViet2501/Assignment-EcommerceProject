package com.assignment.springboot.data.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "vote_star")
    private int voteStar;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Rating(int id, int voteStar, Customer customer, Product product) {
        this.id = id;
        this.voteStar = voteStar;
        this.customer = customer;
        this.product = product;
    }
}
