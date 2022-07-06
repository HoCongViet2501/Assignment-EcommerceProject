package com.assignment.springboot.data.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	private String token;
	private String type = "Bearer ";
	private int id;
	private String email;
	private List<String> roles;
	
	public JwtResponse(String token, int id, String email, List<String> roles) {
		this.token = token;
		this.id = id;
		this.email = email;
		this.roles = roles;
	}
}
