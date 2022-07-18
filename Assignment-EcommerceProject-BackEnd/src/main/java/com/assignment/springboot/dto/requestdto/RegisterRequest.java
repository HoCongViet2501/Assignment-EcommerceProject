package com.assignment.springboot.dto.requestdto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterRequest {
	@NotNull(message = "username.is.required")
	private String userName;
	
	@Email(message = "Incorrect.email")
	@NotBlank(message = "Email.cannot.be.empty")
	private String email;
	
	@Size(min = 6, max = 16, message = "The.password.must.be.between.6.and.16.characters.long")
	private String password;
	
	@Size(min = 6, max = 16, message = "The.password.confirmation.must.be.between.6.and.16.characters.long")
	private String passwordConfirm;
	
}
