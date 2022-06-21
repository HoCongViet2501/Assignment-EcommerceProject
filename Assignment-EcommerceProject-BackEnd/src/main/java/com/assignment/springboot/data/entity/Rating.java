package com.assignment.springboot.data.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
@EqualsAndHashCode
@Entity
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
    public Rating(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoteStar() {
        return voteStar;
    }

    public void setVoteStar(int voteStar) {
        this.voteStar = voteStar;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
