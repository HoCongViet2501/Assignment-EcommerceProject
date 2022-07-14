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
	
}
