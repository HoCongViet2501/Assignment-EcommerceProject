package com.assignment.springboot.data.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class ProductDTO {
    private int id;

    @NotBlank(message = "please.fill.name.of.product")
    private String name;

    private String description;

    @NotNull(message = "please.fill.price.of.product")
    private float price;

    @Min(value = 0, message = "quantity.can.not.negative")
    private int quantity;

    @NotBlank(message = "please.fill.status.of.product")
    private String status;

    private Date createdDate;

    private Date updatedDate;

    private CategoryDTO category;

    private BrandDTO brand;
}
