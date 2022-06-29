package com.assignment.springboot.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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
    @NotNull(message = "please fill last name")
    private String lastName;
    private String gmail;

    @Column(name = "phone_number")
    @NotNull(message = "please fill phone number")
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bank> banks;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rating> ratings;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ShoppingSession> shoppingSessions;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;
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
