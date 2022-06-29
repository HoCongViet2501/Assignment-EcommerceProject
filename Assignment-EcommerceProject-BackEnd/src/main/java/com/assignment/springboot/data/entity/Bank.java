package com.assignment.springboot.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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

    public Bank(Integer id, String bankName, String cardNumber, String cardName) {
        this.id = id;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.cardName = cardName;
    }
}