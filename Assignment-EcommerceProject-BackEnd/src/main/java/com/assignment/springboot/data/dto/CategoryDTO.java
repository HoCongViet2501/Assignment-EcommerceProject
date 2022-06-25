package com.assignment.springboot.data.dto;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class CategoryDTO {
    private int id;
    private String name;
    private String description;
}
