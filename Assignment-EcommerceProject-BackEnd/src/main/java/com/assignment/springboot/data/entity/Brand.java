package com.assignment.springboot.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
@Table(name = "brands")
@Getter
@Setter
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "please fill brand name")
    private String name;
    private String description;
    @NotBlank(message = "please fill phone number of this brand")
    @Column(name = "phone_number")
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    public Brand(int id, String name, String description, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Brand() {

    }
}
