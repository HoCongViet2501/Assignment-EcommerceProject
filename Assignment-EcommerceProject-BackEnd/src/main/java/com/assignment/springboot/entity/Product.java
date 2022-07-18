package com.assignment.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String volume;
    private float price;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    private String status;
    private String gender;
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Images> images;
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartDetails> cartDetails;
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails;
    
    public Product(long id, String name, String volume, float price, int quantity, Date createdDate, Date updatedDate, Category category, Brand brand, String status, String gender) {
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.price = price;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.category = category;
        this.brand = brand;
        this.status = status;
        this.gender = gender;
    }
}
