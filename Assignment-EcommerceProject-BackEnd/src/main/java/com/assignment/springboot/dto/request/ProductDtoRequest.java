package com.assignment.springboot.dto.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class ProductDtoRequest {
	private long id;
	@NotNull(message = "please.fill.name.of.product")
	private String name;
	@NotNull(message = "please.fill.name.of.volume")
	private String volume;
	
	@NotNull(message = "please.fill.price.of.product")
	private float price;
	
	@Min(value = 1, message = "quantity.can.not.negative")
	private int quantity;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	private long categoryId;
	
	private long brandId;
	
	@NotNull(message = "please.fill.status.of.product")
	private String status;
	
	@NotNull(message = "please.fill.name.of.product")
	private String gender;
}
