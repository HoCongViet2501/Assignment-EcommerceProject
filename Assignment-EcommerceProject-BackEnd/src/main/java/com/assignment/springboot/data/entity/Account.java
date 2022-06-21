package com.assignment.springboot.data.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
@EqualsAndHashCode
@Entity
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Account(int id, String userName, String passWord, String role, String gmail, Employee employee, Customer customer) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.gmail = gmail;
        this.employee = employee;
        this.customer = customer;
    }
    public Account(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}