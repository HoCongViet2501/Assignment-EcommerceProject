package com.assignment.springboot.dto.requestdto;

import lombok.*;

import javax.validation.constraints.NotNull;
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class CategoryDtoRequest {
	@NotNull(message = "please.fill.name.of.category")
	private String name;
	private String description;
}
