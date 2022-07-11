package com.assignment.springboot.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class CategoryDTO {
    private int id;
    @NotNull(message = "please.fill.name.of.category")
    private String name;
    private String description;
}
