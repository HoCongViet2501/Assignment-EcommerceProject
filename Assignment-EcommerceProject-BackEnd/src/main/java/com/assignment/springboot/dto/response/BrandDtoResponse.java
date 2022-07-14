package com.assignment.springboot.dto.response;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class BrandDtoResponse {
    private long id;
    private String name;
    private String description;
    private String phoneNumber;
    private String address;

    public BrandDtoResponse(int id, String name, String description, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
}
