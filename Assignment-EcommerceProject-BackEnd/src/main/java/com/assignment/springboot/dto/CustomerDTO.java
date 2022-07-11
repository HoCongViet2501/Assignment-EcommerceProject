package com.assignment.springboot.dto;
import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class CustomerDTO {
    private int id;
    private String firstName;
    @NotNull(message = "please.fill.last.name")
    private String lastName;
    private String gmail;
    @NotNull(message = "please.fill.phone.number")
    private String phoneNumber;
    private String address;
}
