package com.assignment.springboot.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class BrandDtoRequest {
	@NotNull(message = "please.fill.brand.name")
	private String name;
	private String description;
	@NotNull(message = "please.fill.phone.number.of.this.brand")
	private String phoneNumber;
	@NotNull(message = "please.fill.brand.address")
	private String address;
}
