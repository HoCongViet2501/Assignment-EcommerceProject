package com.assignment.springboot.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class CustomerDtoRequest {
	private String firstName;
	@NotNull(message = "please.fill.last.name")
	private String lastName;
	private String gmail;
	@NotNull(message = "please.fill.phone.number")
	private String phoneNumber;
	private String address;
}
