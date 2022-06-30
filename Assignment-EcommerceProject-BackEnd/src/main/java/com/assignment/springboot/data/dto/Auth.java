package com.assignment.springboot.data.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Auth {
    private String token;
    private String role;


    public Auth(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public Auth() {
    }
}
