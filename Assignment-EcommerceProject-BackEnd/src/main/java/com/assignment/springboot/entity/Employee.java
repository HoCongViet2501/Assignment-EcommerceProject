package com.assignment.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String address;
    private boolean sex;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    private String gmail;

    public Employee(int id, String firstName, String lastName, String address, boolean sex, String phoneNumber, Account account, String gmail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.account = account;
        this.gmail = gmail;
    }
}