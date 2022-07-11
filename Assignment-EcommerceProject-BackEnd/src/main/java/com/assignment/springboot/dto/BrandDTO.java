package com.assignment.springboot.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class BrandDTO {
    private int id;
    @NotBlank(message = "please.fill.brand.name")
    private String name;
    private String description;
    @NotBlank(message = "please.fill.phone.number.of.this.brand")
    private String phoneNumber;
    private String address;

    public BrandDTO(int id, String name, String description, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    public BrandDTO(String name, String description, String phoneNumber, String address) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
