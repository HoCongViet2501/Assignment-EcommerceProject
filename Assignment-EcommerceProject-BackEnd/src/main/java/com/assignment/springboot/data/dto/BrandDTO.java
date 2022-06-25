package com.assignment.springboot.data.dto;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class BrandDTO {
    private int id;
    private String name;
    private String description;
    private String phoneNumber;
    private String address;

    public BrandDTO(int id, String name, String description, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
