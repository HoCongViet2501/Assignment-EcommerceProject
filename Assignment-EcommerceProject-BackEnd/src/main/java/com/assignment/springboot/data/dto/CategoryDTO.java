package com.assignment.springboot.data.dto;

import com.assignment.springboot.data.entity.Product;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class CategoryDTO {
    private int id;
    private String name;
    private String description;
    private String address;
    private List<ProductDTO> products;
}
