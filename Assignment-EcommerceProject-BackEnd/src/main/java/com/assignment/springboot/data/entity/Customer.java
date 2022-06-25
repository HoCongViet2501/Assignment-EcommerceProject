package com.assignment.springboot.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String gmail;

    @Column(name = "phone_number")
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bank> banks = new ArrayList<>();
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ShoppingSession> shoppingSessions = new ArrayList<>();
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public Customer(int id, String firstName, String lastName, String gmail, String phoneNumber, String address, Account account) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gmail = gmail;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.account = account;
    }
}
