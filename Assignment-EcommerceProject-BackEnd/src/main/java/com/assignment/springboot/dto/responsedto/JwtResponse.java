package com.assignment.springboot.dto.responsedto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	private String token;
	private String type = "Bearer ";
	private long id;
	private String email;
	private List<String> roles;
	
	public JwtResponse(String token, long id, String email, List<String> roles) {
		this.token = token;
		this.id = id;
		this.email = email;
		this.roles = roles;
	}
}
