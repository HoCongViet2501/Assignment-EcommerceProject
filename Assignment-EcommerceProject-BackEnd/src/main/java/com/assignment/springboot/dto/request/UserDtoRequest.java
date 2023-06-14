package com.assignment.springboot.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDtoRequest {
	@NotNull(message = "username.is.required")
	private String userName;

	@NotNull(message = "password.is.required")
	private String passWord;
}
