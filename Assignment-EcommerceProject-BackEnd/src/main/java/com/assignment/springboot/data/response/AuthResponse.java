package com.assignment.springboot.data.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	private String email;
	private String accessToken;
}
