package com.assignment.springboot.dto.response;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class ProductDtoResponse {
	private long id;
	
	private String name;
	
	private String volume;
	
	private float price;
	
	private int quantity;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	private CategoryDtoResponse category;
	
	private BrandDtoResponse brand;
	
	private String status;
	
	private String gender;
	
	public ProductDtoResponse(long id, String name, String volume, float price, int quantity) {
		this.id = id;
		this.name = name;
		this.volume = volume;
		this.price = price;
		this.quantity = quantity;
	}
}
