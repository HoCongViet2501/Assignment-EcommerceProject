package com.assignment.springboot.dto.response;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class CustomerDtoResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String gmail;
    private String phoneNumber;
    private String address;
}
