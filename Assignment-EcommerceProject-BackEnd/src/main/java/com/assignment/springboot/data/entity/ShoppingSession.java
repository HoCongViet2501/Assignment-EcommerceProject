package com.assignment.springboot.data.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode
@Table(name = "shopping_session")
public class ShoppingSession implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "session",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CartDetail> cartDetails = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
