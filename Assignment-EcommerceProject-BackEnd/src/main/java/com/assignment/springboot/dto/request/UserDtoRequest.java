package com.assignment.springboot.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDtoRequest {
	@NotNull(message = "username.is.required")
	private String username;

	@NotNull(message = "password.is.required")
	private String password;

	@NotNull(message = "password.is.required")
	private String passwordConfirm;

}
