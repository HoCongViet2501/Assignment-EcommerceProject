package com.assignment.springboot.data.dto;

import com.assignment.springboot.data.entity.Brand;
import com.assignment.springboot.data.entity.Category;
import lombok.*;
import java.util.Date;
import java.util.List;
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private float price;
    private String status;
    private Date createdDate;
    private Date updatedDate;
    private CategoryDTO category;
    private BrandDTO brand;
    private List<ImageDTO> images;
}
