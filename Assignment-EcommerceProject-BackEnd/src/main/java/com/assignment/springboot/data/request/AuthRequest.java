package com.assignment.springboot.data.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class AuthRequest {
	@NotNull(message = "required")
	@Email
	@Length(min = 10,max = 50)
	private String gmail;
	@NotNull(message = "required")
	@Length(min = 4,max = 50)
	private String password;
}
