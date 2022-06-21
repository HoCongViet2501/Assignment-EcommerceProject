package com.assignment.springboot.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
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
    @OneToMany
    @JoinColumn(name = "bank_id")
    private Bank bank;
    @OneToMany
    @JoinColumn

}
