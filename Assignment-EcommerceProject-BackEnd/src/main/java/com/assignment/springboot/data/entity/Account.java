package com.assignment.springboot.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String passWord;
    private String role;
    private String gmail;
    @OneToOne
    @JoinColumn(name = "employee_ID")
    private Employee employee;
    @OneToOne
    @JoinColumn(name = "customer_ID")
    private Customer customer;

    public Account(int id, String userName, String passWord, String role, String gmail, Employee employee, Customer customer) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.gmail = gmail;
        this.employee = employee;
        this.customer = customer;
    }

    public Account() {
    }

}