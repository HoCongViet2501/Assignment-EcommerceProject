package com.assignment.springboot.data.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode
@Entity
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "card_name")
    private String cardName;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bank(Integer id, String bankName, String cardNumber, String cardName) {
        this.id = id;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.cardName = cardName;
    }
    public Bank(){

    }
    public String getBankName() {
        return bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardName() {
        return cardName;
    }
}