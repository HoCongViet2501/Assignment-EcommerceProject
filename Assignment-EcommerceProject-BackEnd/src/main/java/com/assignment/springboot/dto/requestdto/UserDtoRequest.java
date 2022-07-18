package com.assignment.springboot.dto.requestdto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDtoRequest {
	@NotNull(message = "username.is.required")
	private String userName;
	
	@NotNull(message = "gmail.is.required")
	private String gmail;
	
	private String phoneNumber;
	
	private String address;
	
	@NotNull(message = "password.is.required")
	private String passWord;
}
